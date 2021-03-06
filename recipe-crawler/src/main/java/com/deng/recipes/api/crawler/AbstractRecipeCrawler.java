package com.deng.recipes.api.crawler;

import com.deng.recipes.api.entity.RecipeEntity;
import com.deng.recipes.api.persit.PersistUtils;
import com.deng.recipes.api.persit.RecipeSaver;
import com.deng.recipes.api.proxys.Proxy;
import com.deng.recipes.api.proxys.ProxyPool;
import com.deng.recipes.api.utils.ConfigManager;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.elasticsearch.common.Strings;

import java.util.regex.Pattern;

/**
 * Created by hcdeng on 2017/4/25.
 */
public abstract class AbstractRecipeCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");

    protected abstract String baseUrl();

    protected abstract String recipePattern();

    protected abstract RecipeEntity processPageContent(String html);

    private String htmlDir() {
        String dataPath = ConfigManager.instance().getProperty("cache.path");
        return dataPath + "/" + baseUrl().hashCode() + "/htmls/";
    }

    private String imgDir() {
        String dataPath = ConfigManager.instance().getProperty("cache.path");
        return dataPath + "/" + baseUrl().hashCode() + "/images/";
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.contains(baseUrl());
    }

    @Override
    public void visit(Page page) {

        String url = page.getWebURL().getURL();

        if (!url.matches(recipePattern()))
            return;

        System.out.println("visiting: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            RecipeEntity recipeEntity = processPageContent(html);
            if (recipeEntity != null) {
                PersistUtils.save(recipeEntity);
                //正式部署不要保存html
                //RecipeSaver.saveHtml(url, html, htmlDir());
            } else {
                System.out.println("something bad happened: " + url);
            }
        }
    }

}
