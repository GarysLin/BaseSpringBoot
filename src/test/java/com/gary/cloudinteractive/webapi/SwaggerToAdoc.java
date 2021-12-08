package com.gary.cloudinteractive.webapi;


import com.gary.cloudinteractive.webapi.utils.FileUtil;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.StringReader;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class SwaggerToAdoc {
    @Autowired
    private MockMvc mockMvc;
//    @Test
    public void generateAsciiDocsToFile() throws Exception {
        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
//        MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs.json")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
//                .withOutputLanguage(Language.ZH)
                .withOutputLanguage(Language.EN)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

//        MockHttpServletResponse response = mvcResult.getResponse();
//        String swaggerJson = response.getContentAsString();
        String swaggerJson = FileUtil.loadFile("api-docs.json");
        System.out.println("xxxxx: "+swaggerJson);
        StringReader a = new StringReader(swaggerJson);
        Swagger swagger = new SwaggerParser().parse(IOUtils.toString(a));
//        System.out.println("swagger: "+swagger);
        Swagger2MarkupConverter.from(swaggerJson)
                .withConfig(config)
                .build()
                .toFile(Paths.get("./src/test/resources/swagger"));
//                .toFile(Paths.get(outputDir + "/swagger"));
        Assert.assertTrue(true);
    }
}
