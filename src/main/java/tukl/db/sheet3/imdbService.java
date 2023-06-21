package tukl.db.sheet3;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class imdbService {
    private final imdbRepository imdbRepo;

    @Autowired
    public imdbService(imdbRepository repo){
        this.imdbRepo = repo;
    }

    public List<Document> allMovies(String collection){
        return imdbRepo.getAllDocs(collection);
    }

    public List<Document> topRatedInGenre (String genre) {
        // TODO: Add fancy processing here, maybe filter out NaN or NotAvailable value
        return imdbRepo.topRatedMoviesInGenre(genre);}
}
