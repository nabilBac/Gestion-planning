package com.securite.planning.converters;



import com.securite.planning.models.Site;
import com.securite.planning.services.SiteService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SiteConverter implements Converter<String, Site> {

    private final SiteService siteService;

    public SiteConverter(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public Site convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(source);
            return siteService.getSiteById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
