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

import java.io.StringReader;
import java.nio.file.Paths;

public class SwaggerToAdoc {

    public static void main(String args[]) throws Exception {
        SwaggerToAdoc swaggerToAdoc = new SwaggerToAdoc();
        swaggerToAdoc.generateAsciiDocsToFile();
    }

    public void generateAsciiDocsToFile() throws Exception {
        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");

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
