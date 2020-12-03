package com.gary.cloudinteractive.webapi.filter;

import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnlyAfterRequestLoggingFilter extends AbstractRequestLoggingFilter {

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {

  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    logger.debug(message);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    boolean isFirstRequest = !isAsyncDispatch(request);
    HttpServletRequest requestToUse = request;

    if (isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
      requestToUse = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
    }

    boolean shouldLog = shouldLog(requestToUse);
    try {
      filterChain.doFilter(requestToUse, response);
    }
    finally {
      if (shouldLog && !isAsyncStarted(requestToUse)) {
        afterRequest(requestToUse, getAfterMessage(requestToUse));
      }
    }
  }

  /**
   * Get the message to write to the log after the request.
   * @see #createMessage
   */
  private String getAfterMessage(HttpServletRequest request) {
    return createMessage(request, DEFAULT_AFTER_MESSAGE_PREFIX, DEFAULT_AFTER_MESSAGE_SUFFIX);
  }
}
