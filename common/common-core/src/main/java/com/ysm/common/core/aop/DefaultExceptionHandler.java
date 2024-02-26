package com.ysm.common.core.aop;

import com.ysm.common.core.domain.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public CommonResult<Void> handlerIllegalArgumentException(IllegalArgumentException ex) {
		log.error("IllegalArgumentException", ex);
		return CommonResult.fail(ex.getMessage());
	}


	@ExceptionHandler(IllegalStateException.class)
	@ResponseBody
	public CommonResult<Void> handlerIllegalStateException(IllegalStateException ex) {
		log.error("IllegalStateException", ex);
		return CommonResult.fail(ex.getMessage());
	}

	@ExceptionHandler(SQLSyntaxErrorException.class)
	@ResponseBody
	public CommonResult<Void> handlerSqlSyntaxErrorException(SQLSyntaxErrorException ex) {
		log.error("SQLSyntaxErrorException", ex);
		return CommonResult.fail(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public CommonResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("MethodArgumentNotValidException", ex);
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> errorMessages = fieldErrors.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return CommonResult.fail(String.join(";", errorMessages));
	}

//	@ExceptionHandler(ValidationException.class)
//	@ResponseBody
//	public CommonResult<Void> handleValidationException(ValidationException ex) {
//		log.error("MethodArgumentNotValidException", ex);
//		return CommonResult.fail(ex.getCause().getMessage());
//	}
//
//	@ExceptionHandler(ConstraintViolationException.class)
//	@ResponseBody
//	public CommonResult<Void> handleConstraintViolationException(ConstraintViolationException ex) {
//		log.error("ConstraintViolationException", ex);
//		return CommonResult.fail(ex.getMessage());
//	}
//
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseBody
//	public CommonResult<Void> handlerNoFoundException(Exception ex) {
//		log.error("NoHandlerFoundException", ex);
//		return CommonResult.fail("路径不存在，请检查路径是否正确");
//	}
//
//	@ExceptionHandler(DuplicateKeyException.class)
//	@ResponseBody
//	public CommonResult<Void> handleDuplicateKeyException(DuplicateKeyException ex) {
//		log.error("DuplicateKeyException", ex);
//		return CommonResult.fail("数据重复，请检查后提交");
//	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CommonResult<Void> handleException(Exception ex) {
		log.error("Exception", ex);
		return CommonResult.fail("系统繁忙，请稍后再试！错误信息：" + ex.getLocalizedMessage());
	}
}