package kg.peaksoft.bilingualb4.api.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestResponse {

    private String id;
    private String title;
    private String shortDescription;
}
