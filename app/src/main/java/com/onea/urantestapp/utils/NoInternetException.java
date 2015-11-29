package com.onea.urantestapp.utils;

public final class NoInternetException extends Throwable {

  public NoInternetException() {
  }

  public NoInternetException(String detailMessage) {
    super(detailMessage);
  }

  public NoInternetException(String detailMessage, Throwable cause) {
    super(detailMessage, cause);
  }

  public NoInternetException(Throwable cause) {
    super(cause);
  }

}
