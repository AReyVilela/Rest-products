package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFound extends RuntimeException{


    public ProductoNotFound (long id){
        super("El producto con esa id no existe: "+ id);
    }
}
