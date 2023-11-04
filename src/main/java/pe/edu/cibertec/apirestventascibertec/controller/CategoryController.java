package pe.edu.cibertec.apirestventascibertec.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.apirestventascibertec.exception.ResourceNotFoundException;
import pe.edu.cibertec.apirestventascibertec.model.bd.Category;
import pe.edu.cibertec.apirestventascibertec.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = {"https://cibertec.blackboard.com", "https://intranet.cibertec.edu.pe"})
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    private CategoryService categoryService;


    @GetMapping("")
    public ResponseEntity<List<Category>> listarCategorias(){
        List<Category> categoryList = new ArrayList<>();
        categoryService.listarCategorias()
                .forEach(categoryList::add);
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> obtenerCategoria(
            @PathVariable("id") Integer id){
        Category category = categoryService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /*@GetMapping("/categoryname/{categoryname}")
    public ResponseEntity<Category> obtenerCategoriaPorNombre(
            @PathVariable("categoryname") String categoryName){
        Category category = categoryService
                .obtenerCatogoriaPorNombre(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el nombre "+
                        categoryName + " no existe."));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }*/

    @GetMapping("/categoryname/{filtro}")
    public ResponseEntity<List<Category>> filtrarCategoriasPorNombre(
            @PathVariable("filtro") String filtro
    ){
        List<Category> categoryList = new ArrayList<>();
        categoryService.obtenerCategoriasPorFiltro(filtro)
                .forEach(categoryList::add);
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Category> registrarCategoria(
            @RequestBody Category category
    ){
        return new ResponseEntity<>(
                categoryService.guardar(category), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> actualizarCategoria(
            @PathVariable("id") Integer id,
            @RequestBody Category category
    ){
        Category oldCategory = categoryService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));
        oldCategory.setCategoryname(category.getCategoryname());
        oldCategory.setDescription(category.getDescription());
        return new ResponseEntity<>(
                categoryService.guardar(oldCategory), HttpStatus.OK
        );
    }

}
