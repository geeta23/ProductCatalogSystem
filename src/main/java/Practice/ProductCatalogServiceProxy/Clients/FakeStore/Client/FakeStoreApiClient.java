package Practice.ProductCatalogServiceProxy.Clients.FakeStore.Client;

import Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos.FakeStoreProductDto;
import Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos.FakeStoreRatingDto;
import Practice.ProductCatalogServiceProxy.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreApiClient {
    // it is the library provided by spring and internally invokes the http client
    // which will call API's for us similarly how I implemented in front end.

    // when I hit /products endpoint from front end, then that will hit controller
    // then inside controller we a ProductService to call and inside product service
    // we are calling a third party API's to fetch data for now i.e why we named the project as proxy
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    // if {id} and productId are same then we can directly give @PathVariable() else we have to give
    // @PathVariable("id") to help in mapping

    // jackson takes care of conversion of json into object and object to json, as we see some null in
    // fields jackson will take care of mapping the keys with objects
    public FakeStoreProductDto getProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // (url to hit, response type of class, external variables like product id where it will replace {id})
        // getForEntity is returning response entity so we did getBody to get only the body
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId).getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto[] getProducts(){
        //return "Returning all products.";
        RestTemplate restTemplate = restTemplateBuilder.build();

        // since List<ProductDTO> is generic spring is unable to identify the when we write List<ProductDTO>.class type,
        // use array instead and json allow array in them
        // generics like list are unable to identify determine the type of class at run time, so we are using array.
        FakeStoreProductDto[] fakeStoreproductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        return fakeStoreproductDtos;
    }

    public FakeStoreProductDto createProducts(FakeStoreProductDto fakeStoreProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        //FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", product, FakeStoreProductDto.class, id);
        ResponseEntity<FakeStoreProductDto> responseEntityfakeStoreProductDto = responseForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", fakeStoreProductDto, FakeStoreProductDto.class, id);
        return responseEntityfakeStoreProductDto.getBody();
    }

    // since inbuild is not fakestore APi is not working we created our own
    private <T> ResponseEntity<T> responseForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (ResponseEntity)restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
