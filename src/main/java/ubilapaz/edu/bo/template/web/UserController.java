package ubilapaz.edu.bo.template.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubilapaz.edu.bo.template.dto.Response;
import ubilapaz.edu.bo.template.dto.UserDto;
import ubilapaz.edu.bo.template.dto.UsuarioDto;
import ubilapaz.edu.bo.template.entity.User;
import ubilapaz.edu.bo.template.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("Hola mundo!!", HttpStatus.OK);
    }

    @GetMapping("/testDto")
    public ResponseEntity<Response> testDto(){
        Response response = new Response();
        response.setEstado(true);
        response.setMensaje("exito<");

        UsuarioDto u = new UsuarioDto();
        u.setNombre("pablo");
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<UserDto> dtoGet(@PathVariable Integer id){
        String saludo="hola";
        User user= userRepository.getOne(id);
        UserDto u = new UserDto();
        u.setNombre(user.getNombre());
        u.setUsername(user.getUsername());
        return new ResponseEntity<UserDto>(u, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UserDto userDto){
        String mensaje="registro exitoso";
        System.out.println(userDto);

        User u = new User();
        u.setNombre(userDto.getNombre());
        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());

        userRepository.save(u);

        //mensaje="registro fallido";
        return new ResponseEntity<String>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable Integer id, @RequestBody UserDto userDto){
        String mensaje="actualizacion exitosa";
        System.out.println(id);
        System.out.println(userDto);

        User u= userRepository.getOne(id);
        u.setNombre(userDto.getNombre());
        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());
        userRepository.save(u);

        //mensaje="actualizacion fallida";
        return new ResponseEntity<String>(mensaje, HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        String mensaje="actualizacion exitosa";
        System.out.println(id);

        userRepository.deleteById(id);
        //mensaje="actualizacion fallida";
        return new ResponseEntity<String>(mensaje, HttpStatus.OK);
    }
}
