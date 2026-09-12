package com.codereviewer.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;

public class SqlInjectionAnalyzer implements CodeAnalyzer {

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {

        List<codeIssue> issues = new ArrayList<>();

        cu.findAll(BinaryExpr.class).forEach(expr -> {

            String code = expr.toString().toLowerCase();

            if ((code.contains("select")
                    || code.contains("insert")
                    || code.contains("update")
                    || code.contains("delete"))
                    && code.contains("+")) {

                issues.add(new codeIssue(
                        "JAVA013",
                        "HIGH",
                        expr.getBegin().map(p -> p.line).orElse(-1),
                        "Possible SQL Injection.",
                        "Use Prepared Statement instead of string concatenation."
                ));
            }
        });

        return issues;
    }
}