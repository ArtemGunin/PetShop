package ua.com.gunin.NIX11.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.com.gunin.NIX11.dto.UserDTO;
import ua.com.gunin.NIX11.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO fromUser(User user);

    List<User> toUserList(List<UserDTO> userDTOS);

    List<UserDTO> fromUserList(List<User> users);
}
