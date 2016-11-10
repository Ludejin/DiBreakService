package com.zero.controller;

import com.zero.business.user.IUserService;
import com.zero.common.RestResult;
import com.zero.common.RestResultGenerator;
import com.zero.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jin_ on 2016/11/10.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {
  @Autowired
  private IUserService userService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public RestResult<List<User>> all() {
    List<User> all = userService.findAll();
    return RestResultGenerator.genSuccessResult(all);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<User> get(@PathVariable Long id) throws Exception {
    User user = userService.findById(id);
    return RestResultGenerator.genSuccessResult(user);
  }
}
