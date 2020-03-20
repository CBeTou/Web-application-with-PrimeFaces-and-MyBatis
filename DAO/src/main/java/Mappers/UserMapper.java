package Mappers;

import DTO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO User(USER_NAME, BIRTH_DAY, USER_GENDER) VALUES (#{userName}, #{birthDate}, #{userGender})")
    @Options(useGeneratedKeys=true, keyProperty="userId")
    public void insertUser(User user);

    @Select("SELECT USER_ID AS userId, USER_NAME AS userName, BIRTH_DAY AS birthDate, USER_GENDER AS userGender FROM USER WHERE USER_ID=#{userId}")
    public User getUserById(Integer userId);

    @Select("SELECT USER_ID, USER_NAME, BIRTH_DAY, USER_GENDER FROM USER")
    @Results({
            @Result(id=true, property="userId", column="USER_ID"),
            @Result(property="userName", column="USER_NAME"),
            @Result(property="birthDate", column="BIRTH_DAY"),
            @Result(property = "userGender", column = "USER_GENDER")
    })
    public List<User> getAllUsers();

    @Update("UPDATE USER SET USER_NAME=#{userName}, BIRTH_DAY=#{birthDate}, USER_GENDER=#{userGender} WHERE USER_ID=#{userId}")
    public void updateUser(User user);

    @Delete("DELETE FROM USER WHERE USER_ID=#{userId}")
    public void deleteUser(Integer userId);
}