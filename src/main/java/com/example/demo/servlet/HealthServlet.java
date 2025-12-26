package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200); [cite: 11]
        resp.setContentType("text/plain"); [cite: 12]
        PrintWriter writer = resp.getWriter(); [cite: 12]
        writer.write("BUNDLE-OK"); [cite: 12]
        writer.flush(); [cite: 12]
    }
}