package com.company.dto;


import com.company.exception.SysError;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponeJson<T> implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;
  private String message;
  private transient T data;
  private SysError sysError;
  private HttpStatus status;

  public ResponeJson() {

  }

  public ResponeJson(String message, T data, SysError sysError, HttpStatus status) {
    super();
    this.message = message;
    this.data = data;
    this.sysError = sysError;
    this.status = status;
  }

  public ResponeJson(String message, SysError sysError) {
    super();
    this.message = message;
    this.sysError = sysError;
  }

  public ResponeJson(SysError sysError) {
    super();
    this.sysError = sysError;
  }

  public ResponeJson(T data) {
    this.data = data;
  }

  public ResponeJson(T data, HttpStatus status, String message) {
    this.data = data;
    this.status = status;
    this.message = message;
  }

  public ResponeJson(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public ResponeJson(String message) {
    super();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public SysError getSysError() {
    return sysError;
  }

  public void setSysError(SysError sysError) {
    this.sysError = sysError;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }


}
