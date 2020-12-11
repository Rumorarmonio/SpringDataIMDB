package pack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import pack.model.DirectorIdNameProb;
import pack.model.Director;
import pack.repository.DirectorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller /*контроллер принимает и обрабатывает запросы от пользователя, обменивается данными с моделью,
            показывает пользователю нужное представление и переадресовывает на другие страницы*/
public class MainController {

    @Autowired //говорит спрингу, что в это поле нужно внедрить зависимость - другой бин
    private DirectorJpaRepository directorRepository;

    @GetMapping("/") /*мэппинги связывают метод контроллера с адресом, по которому можно к нему обратиться (из браузера)*/
    public String mainPage(Model model) {
        List<String> genres = directorRepository.getGenres();
        model.addAttribute("genres", genres);
        return "main_page";
    }

    @GetMapping("/list_of_directors") /*@RequestParam("numberOfLines") Integer numberOfLines, @RequestParam("genre") String genres - получение значений при помощи аннотаций*/
    public String directors(HttpServletRequest request, Pageable page, Model model, Sort sort) {
        //получение значений из запроса
        Integer numberOfLines = Integer.valueOf(request.getParameter("numberOfLines"));
        String genre = request.getParameter("genre");

        PageRequest pageRequest = new PageRequest(page.getPageNumber(), numberOfLines == null ? 5 : numberOfLines, sort);
        Page<DirectorIdNameProb> directors = directorRepository.findAllByGenre(genre, pageRequest);

        model.addAttribute("directors", directors);

        Sort.Order order = null;
        if (sort != null)
            order = sort.iterator().next();

        model.addAttribute("sort", sort != null ? order.getProperty() : "");
        model.addAttribute("dir", sort != null ? order.getDirection() : "");
        return "list_of_directors";
    }

    @GetMapping("/director_page") /*@RequestParam("id") Integer id*/
    private String director(HttpServletRequest request, Model model) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Director director = directorRepository.findDirectorById(id);
        model.addAttribute("director", director);
        return "director_page";
    }
}