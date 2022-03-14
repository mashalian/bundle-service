package se.seb.bundleservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.seb.bundleservice.model.ModifySuggestedBundleRequest;
import se.seb.bundleservice.model.QuestionRequest;
import se.seb.bundleservice.model.Suggestion;
import se.seb.bundleservice.service.BundleService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@Validated
public class BundleController {
    private final BundleService bundleService;

    @PostMapping("/suggest")
    @Operation(summary = "Suggest bundle for customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Suggested successfully"),
            @ApiResponse(responseCode = "451", description = "Unable to suggest any bundle due to legal reasons", content = @Content)})
    public ResponseEntity<Suggestion> suggestBundle(@Valid @RequestBody QuestionRequest request) {
        return ResponseEntity.status(CREATED)
                .body(bundleService.suggestBundle(request));
    }

    @PutMapping("/customize")
    @Operation(summary = "Customize suggested bundle by customer")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Customized suggestion successfully"),
            @ApiResponse(responseCode = "451", description = "Unable to customize suggestion due to legal reasons", content = @Content)})
    public ResponseEntity<Suggestion> customizeSuggestion(@Valid @RequestBody ModifySuggestedBundleRequest request) {
        return ResponseEntity.accepted()
                .body(bundleService.modifySuggestedBundle(request));
    }
}