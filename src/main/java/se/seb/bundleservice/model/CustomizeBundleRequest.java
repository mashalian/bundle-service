package se.seb.bundleservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizeBundleRequest {

    @NotNull
    Bundle bundle;
    @NotNull
    QuestionRequest questionRequest;
    List<Product> removeProducts;
    List<Product> addProducts;
}
