package com.ms.rest.modules.user.controller.impl;

import com.ms.rest.modules.user.controller.ro.UserRO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    @GetMapping(value = "{id}")
    public void queryById(@PathVariable(value = "id") Long id){

    }

    @ApiOperation(value = "新增或修改")
    @PutMapping
    public void upsert(@Validated @RequestBody UserRO args){

    }

}
