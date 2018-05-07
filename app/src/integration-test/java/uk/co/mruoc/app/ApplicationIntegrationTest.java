package uk.co.mruoc.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.co.mruoc.properties.ClasspathFileContentLoader;
import uk.co.mruoc.properties.FileContentLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class ApplicationIntegrationTest {

    private final FileContentLoader contentLoader = new ClasspathFileContentLoader();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRenderSwagger() throws Exception {
        String expectedSwagger = contentLoader.loadContent("/expected-swagger.json");

        MvcResult result = this.mockMvc.perform(get("/v2/api-docs")).andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(expectedSwagger);
    }

}
