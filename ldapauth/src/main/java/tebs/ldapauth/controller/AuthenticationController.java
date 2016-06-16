package tebs.ldapauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tebs.ldapauth.dto.TEBSUserDto;
import tebs.ldapauth.dto.UserAuthenticationDto;
import tebs.ldapauth.dto.UserAuthenticationRequest;
import tebs.ldapauth.dto.UserAuthenticationResponse;
import tebs.ldapauth.dto.FindUserRequest;
import tebs.ldapauth.dto.FindUserResponse;
import tebs.ldapauth.dto.ModifyUserRequest;
import tebs.ldapauth.dto.ModifyUserResponse;
import tebs.ldapauth.dto.PersistUserRequest;
import tebs.ldapauth.dto.PersistUserResponse;
import tebs.ldapauth.dto.TEBSModifyUserDto;
import tebs.ldapauth.service.AuthenticationServiceImpl;

@Controller
@RequestMapping("/tebs/auth/rest")
public class AuthenticationController {
	@Autowired
	AuthenticationServiceImpl authenticationService;
	
	@RequestMapping(value="/getuserinfo", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody FindUserResponse getUserListing(@RequestBody FindUserRequest findUserRequest){
		FindUserResponse response = new FindUserResponse();
//		FindUserRequest request = new FindUserRequest();
//		request.setFindUserDto(userDto);
		response = authenticationService.findTEBSUsers(findUserRequest);
		return response;
	}
	
	@RequestMapping(value="/getallusers", method=RequestMethod.GET)
	public @ResponseBody FindUserResponse getAllUsers(){
		FindUserResponse response = new FindUserResponse();
		response = authenticationService.findAllTEBSUsers();
		return response;
	}
	
	@RequestMapping(value="/createuser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody PersistUserResponse createUser(@RequestBody TEBSUserDto userDto){
		PersistUserResponse response = new PersistUserResponse();
		PersistUserRequest request = new PersistUserRequest();
		request.setUserDto(userDto);
		response = authenticationService.createTEBSUser(request);
		return response;
	}
	
	@RequestMapping(value="/modifyuser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ModifyUserResponse modifyUser(@RequestBody TEBSModifyUserDto userDto){
		ModifyUserResponse response = new ModifyUserResponse();
		ModifyUserRequest request = new ModifyUserRequest();
		request.setUserDto(userDto);
		response = authenticationService.modifyTEBSUser(request);
		return response;
	}
	
	@RequestMapping(value="/authenticateuser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody UserAuthenticationResponse authenticateUser(@RequestBody UserAuthenticationDto userDto){
		UserAuthenticationResponse response = new UserAuthenticationResponse();
		UserAuthenticationRequest request = new UserAuthenticationRequest();
		request.setUserDto(userDto);
		response = authenticationService.authenticateUser(request);
		return response;
	}
}
