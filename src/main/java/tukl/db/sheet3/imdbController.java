package tukl.db.sheet3;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class imdbController {
    private final imdbService imdbService;

    @Autowired
    public imdbController(imdbService service){
        this.imdbService = service;
    }

    @GetMapping("/allMovies")
    public List<Document> printMovies(){
        return imdbService.allMovies("movie_details");
    }

    @GetMapping("/topRated/{genre}")
    public List<Document> topMoviesGenre (@PathVariable("genre") String genre){
        return imdbService.topRatedInGenre(genre);
    }
}
