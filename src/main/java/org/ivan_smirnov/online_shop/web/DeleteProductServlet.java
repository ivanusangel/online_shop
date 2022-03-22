package org.ivan_smirnov.online_shop.web;

import org.ivan_smirnov.online_shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    private final ProductService productService;

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                throw new RuntimeException("Cannot delete product with empty id");
            }
            productService.delete(Integer.parseInt(id));
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/products");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();  //log
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        resp.sendRedirect("/products");
    }
}