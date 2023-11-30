package ms.triones.backend.rest.domains.user.support.mapper;

import ms.triones.backend.core.modules.user.dao.criteria.UserCriteria;
import ms.triones.backend.rest.domains.user.controller.query.UserQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserCriteria from(UserQuery args);
}
