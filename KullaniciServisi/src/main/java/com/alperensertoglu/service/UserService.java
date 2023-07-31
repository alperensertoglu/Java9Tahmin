package com.alperensertoglu.service;

import com.alperensertoglu.dto.request.UserLoginRequestDto;
import com.alperensertoglu.dto.request.UserSaveRequestDto;
import com.alperensertoglu.exception.EerrorType;
import com.alperensertoglu.exception.UserServiceException;
import com.alperensertoglu.mapper.IUserMapper;
import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.repository.IUserRepository;
import com.alperensertoglu.repository.entity.User;
import com.alperensertoglu.utility.JwtTokenManager;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final IUserRepository repository;
    private final JwtTokenManager jwtTokenManager;

    /**
     * Constructor injeksiyon için fieldları parametre olarak aldık. Klasik yaptığımız
     *
     * @param repository
     * @param jwtTokenManager
     */
    public UserService(IUserRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    /**
     * Kullanıcı adının sistemde olup olmadığını kontrol ediyoruz. Varsa hata fırlatıp yoksa kaydediyoruz
     *
     * @param dto
     * @return
     */
    public User register(UserSaveRequestDto dto) {
        if (repository.existsByUsername(dto.getUsername()))
            throw new UserServiceException(EerrorType.REGISTER_USERNAME_EXISTS);
        User user = IUserMapper.INSTANCE.toUser(dto);
        return save(user);
    }

    /**
     * Jpa repository sayesinde veritabanında dto'nun username ve şifresi var mı ve eşleşiyor mu diye kontrol edip ona göre giriş işlemi gerçekleştiriyoruz.
     * Doğruysa token veriyoruz
     *
     * @param dto
     * @return
     */
    public String doLogin(UserLoginRequestDto dto) {
        Optional<User> user = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isEmpty())
            throw new UserServiceException(EerrorType.DOLOGIN_USERNAMEORPASSWORD_NOTEXISTS);

        return jwtTokenManager.createToken(user.get().getId()).get();

    }

    /**
     * Herkesin findAll işlemi yapamaması için token istiyoruz. Eğer token geçerli ise yapabiliyor
     *
     * @param token
     * @return
     */
    public List<User> findAll(String token) {
        Optional<Long> id = null;
        try {
            id = jwtTokenManager.getIdFromToken(token);
        } catch (Exception exception) {
            throw new UserServiceException(EerrorType.INVALID_TOKEN);
        }
        if (findById(id.get()).isEmpty())
            throw new UserServiceException(EerrorType.INVALID_TOKEN);
        return findAll();
    }

    /**
     * Güncelleme işlemi için kullanıcı ismine göre eğer varsa onun email ve şifresini değiştirebiliyoruz. Fakat senaryoda
     * kullanıcı adı değişmiyor
     *
     * @param dto
     * @return
     */
    public User updateDto(UserSaveRequestDto dto) {
        Optional<User> user = repository.findOptionalByUsername(dto.getUsername());
        if (user.isEmpty())
            throw new UserServiceException(EerrorType.USER_NOT_FOUND);
        user.get().setEmail(dto.getEmail());
        user.get().setPassword(dto.getPassword());
        save(user.get());
        return user.get();

    }

    /**
     * Gelen modeldeki userid burdaki id'ye eşit. O yüzden findById yapıp gelen kullanıcın skorunu modelin skoruna eşitliyoruz
     *
     * @param model
     */
    public void saveModel(GetScoreModel model) {
        User user = repository.findById(model.getUserid()).get();
        user.setScore(model.getScore());
        save(user);
    }
}
