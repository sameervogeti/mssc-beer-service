package guru.springframework.msscbeerservice.web.controller;


import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.enums.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {
    private static Integer DEFAULT_PAGE_NUMBER=0;
    private static Integer DEFAULT_PAGE_SIZE=25;
    private final BeerService beerService;

    @GetMapping(produces = { "application/json" }, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber",required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName",required = false) String beerName,
                                                   @RequestParam(value = "beerStyle",required = false) BeerStyleEnum beerStyle)
    {
        if(pageNumber==null || pageNumber<0)
        {
            pageNumber=DEFAULT_PAGE_NUMBER;
        }
        if(pageSize==null || pageSize<0)
        {
            pageSize=DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList=beerService.listBeers(beerName,beerStyle, PageRequest.of(pageNumber,pageSize));
        return new ResponseEntity<>(beerList,HttpStatus.OK);

    }


    @GetMapping({"/{beerId}"})
    ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity handlePost(@Validated @RequestBody BeerDTO beerDTO) {
        BeerDTO savedDTO = beerService.saveBeer(beerDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/api/v1/beer");
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    ResponseEntity handlePut(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDTO beerDTO) throws ChangeSetPersister.NotFoundException {
        beerService.updateBeerByID(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeerbyId(beerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        System.out.println("Inside Exception Handler");
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
