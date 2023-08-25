package Controller;

import Service.ReactPostService;
import lombok.*;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Getter
@Controller
public class ReactPostController {
    private ReactPostService reactPostService;

}
