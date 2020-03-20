package Service;

import DTO.User;
import Mappers.UserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;


import java.util.ArrayList;
import java.util.List;

public class UserService {

    public void insertUser(User user)
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
            sqlSession.commit();
        }  catch (PersistenceException e)
        {
            e.printStackTrace();
        }  finally
        {
            if (sqlSession != null)
            sqlSession.close();
        }
    }

    public User getUserById(Integer userId)
    {
        User user = new User();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.getUserById(userId);
        }  catch (PersistenceException e)
        {
            e.printStackTrace();
        } finally
        {
            if (sqlSession != null)
            sqlSession.close();
        }
        return user;
    }

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            users = userMapper.getAllUsers();
        }  catch (PersistenceException e)
        {
            e.printStackTrace();
        } finally
        {
            if (sqlSession != null)
            sqlSession.close();
        }
        return users;
    }

    public void updateUser(User user)
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            sqlSession.commit();
        }  catch (PersistenceException e)
        {
            e.printStackTrace();
        } finally
        {
            if (sqlSession != null)
            sqlSession.close();
        }
    }

    public void deleteUser(Integer userId)
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser(userId);
            sqlSession.commit();
        } catch (PersistenceException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (sqlSession != null)
            sqlSession.close();
        }
    }

}