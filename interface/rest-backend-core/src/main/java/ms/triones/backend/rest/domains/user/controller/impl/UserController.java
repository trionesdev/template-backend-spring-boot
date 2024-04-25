package ms.triones.backend.rest.domains.user.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ms.triones.backend.core.modules.user.dao.criteria.UserCriteria;
import ms.triones.backend.rest.domains.user.controller.query.UserQuery;
import ms.triones.backend.rest.domains.user.controller.ro.UserCreateRO;
import ms.triones.backend.rest.domains.user.controller.ro.UserUpdateRO;
import ms.triones.backend.rest.domains.user.controller.vo.UserVO;
import ms.triones.backend.rest.domains.user.support.mapper.UserConvertMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ms.triones.backend.rest.domains.user.support.UserConstants.USER_URI;

@Tag(name = "用户")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = USER_URI)
public class UserController {

    @Operation(summary = "分页")
    @GetMapping("users")
    public PageInfo<UserVO> page(@RequestBody UserQuery args) {
        UserCriteria criteria = UserConvertMapper.INSTANCE.from(args);
        //todo
        return null;
    }

    @Operation(summary = "新增")
    @PostMapping("users")
    public void create(@RequestBody UserCreateRO args) {
        //todo
    }

    @Operation(summary = "编辑")
    @PutMapping("users")
    public void update(@RequestBody UserUpdateRO args) {
        //todo
    }

    @Operation(summary = "详情")
    @GetMapping(value = "users/{id}")
    public UserVO getById(@PathVariable(value = "id") String id) {
        //todo
        return null;
    }

    @Operation(summary = "批量删除，ids使用,分割拼接，形如：1,2,3")
    @DeleteMapping(value = "users/{ids}")
    public void deleteById(@PathVariable(value = "ids") List<String> ids) {
        //todo
    }
}
