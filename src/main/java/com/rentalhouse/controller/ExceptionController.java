package com.rentalhouse.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {

//	@ExceptionHandler(IOException.class)
//	public String handleIOException(IOException ioexception, HttpServletRequest request){
//		return ClassUtils.getShortName(ioexception.getClass());
//	}
}
