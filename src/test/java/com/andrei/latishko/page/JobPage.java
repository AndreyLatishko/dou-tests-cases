package com.andrei.latishko.page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class JobPage {

    public ElementsCollection getAllVacancies() {
        return $$x("//li[@class='l-vacancy']//div[@class='title']//a[@class='vt']");
    }

    public void searchForJobs(String specialization, String jobPosition) {
        $x(String.format("//option[@value='%s']", specialization)).click();
        $x("//input[@class='job']").setValue(jobPosition).pressEnter();
    }

    public void openTop50CompanyTab() {
        $x("//li[@class='top']//a").click();
    }

    public void selectCompany(String titleCompany) {
        $x(String.format("//*[@alt='%s']", titleCompany)).click();
    }

    public ArrayList<String> getAllLinks(ElementsCollection collection){
        ArrayList<String> jobLinks = new ArrayList<>();
        collection.asFixedIterable().forEach(x -> jobLinks.add(x.getAttribute("text")));
        return jobLinks;
    }

    public String titleJob(String href){
        Selenide.open(href);
        return String.valueOf($x("//h1[@class='g-h2']"));
    }
}
