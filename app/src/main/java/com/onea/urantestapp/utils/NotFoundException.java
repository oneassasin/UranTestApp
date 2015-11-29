package com.onea.urantestapp.utils;

public final class NotFoundException extends Throwable {

  public NotFoundException() {
  }

  public NotFoundException(String detailMessage) {
    super(detailMessage);
  }

  public NotFoundException(String detailMessage, Throwable cause) {
    super(detailMessage, cause);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }

}
