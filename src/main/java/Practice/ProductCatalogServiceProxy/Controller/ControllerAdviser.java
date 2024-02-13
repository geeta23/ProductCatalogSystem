package Practice.ProductCatalogServiceProxy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// we will advice the error handling exception
@RestControllerAdvice
public class ControllerAdviser {
    // we can specify for which two error we can call this function and return what status
   // @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    // to make it generic
    @ExceptionHandler({Exception.class})
    private ResponseEntity<String> handleException(){
        return new ResponseEntity<String>("kuch tho phata h", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
