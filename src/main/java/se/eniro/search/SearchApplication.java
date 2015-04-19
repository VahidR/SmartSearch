/**
 * Copyright (C) 2015  Vahid Rafiei (@vahid_r)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package se.eniro.search;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.eniro.search.conf.SearchConfiguration;
import se.eniro.search.resources.SearchResource;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class SearchApplication extends Application<SearchConfiguration> {
	
	private static final Logger log = LoggerFactory.getLogger(SearchApplication.class);
    
	@Override
    public String getName() {
    	return "Smart Search";
    }
    
    @Override
    public void initialize(Bootstrap<SearchConfiguration> bootstrap) {
    	
    }
    
    @Override
    public void run(SearchConfiguration conf, Environment env) throws ClassNotFoundException, Exception {
        Injector injector = Guice.createInjector();
        env.jersey().register(injector.getInstance(SearchResource.class));
    }
    
    public static void main( String[] args ) throws Exception {
        new SearchApplication().run(args);
    }
    
    
       
}
