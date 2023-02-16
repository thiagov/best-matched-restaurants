package com.thiagov.restaurantsapi.exceptions;

public class InvalidFilterException extends RuntimeException {
  public InvalidFilterException(String message) {
    super(message);
  }
}
