package org.ivan_smirnov.online_shop.web;

import org.ivan_smirnov.online_shop.entity.Product;
import org.ivan_smirnov.online_shop.service.ContentReader;
import org.ivan_smirnov.online_shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {
    private static final String ADD_HTML = "/templates/update.html";
    private final ProductService productService;
    private final ContentReader contentReader;

    public AddProductServlet(ProductService productService, ContentReader contentReader) {
        this.productService = productService;
        this.contentReader = contentReader;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            Product product;
            if (id == null || id.isEmpty()) {
                product = Product.builder().name("").build();
            } else {
                product = productService.getProductById(Integer.parseInt(id));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("id", product.getId());
            pageVariables.put("name", product.getName());
            pageVariables.put("price", product.getPrice());
            PageGenerator.getPage("update.html", pageVariables, response.getWriter());
        } catch (FileNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace(); //log
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();  //log
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        productService.save(Product.builder()
                .id(id == null || id.isEmpty() ? 0 : Integer.parseInt(id))
                .name(name)
                .price(price == null || price.isEmpty() ? 0.0 : Double.parseDouble(price))
                .build());
        resp.sendRedirect("/products");
    }
}