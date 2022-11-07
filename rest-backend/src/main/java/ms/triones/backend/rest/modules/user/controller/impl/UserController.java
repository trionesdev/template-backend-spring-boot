package ms.triones.backend.rest.modules.user.controller.impl;

import ms.triones.backend.rest.modules.user.controller.ro.UserRO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    @GetMapping(value = "{id}")
    public void queryById(@PathVariable(value = "id") Long id){

    }

    @Operation(summary = "新增或修改")
    @PutMapping
    public void upsert(@Validated @RequestBody UserRO args){

    }

}
