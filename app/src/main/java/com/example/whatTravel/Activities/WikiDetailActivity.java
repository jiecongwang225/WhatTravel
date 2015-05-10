package com.example.whatTravel.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.example.whatTravel.API.WTAPIConstants;
import com.example.whatTravel.API.WTApiLoadManager;
import com.example.whatTravel.Models.wiki.WikiGeoQueryResult;
import com.example.whatTravel.Models.wiki.WikiGeoSearch;
import com.example.whatTravel.Models.wiki.WikiGeoSearchResult;
import com.example.whatTravel.Models.wiki.WikiGeoSearchResults;
import com.example.whatTravel.Models.wiki.WikiPageSearch;
import com.example.whatTravel.R;
import com.example.whatTravel.helpers.ResponseParser;
import com.example.whatTravel.utils.Utils;
import com.example.whatTravel.utils.WTLog;
import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class WikiDetailActivity extends ActionBarActivity implements WTApiLoadManager.DataLoadLister{
    private final String LOG_TAG = WikiDetailActivity.class.getSimpleName();
    private WTApiLoadManager mloadManager = WTApiLoadManager.getInstance();
    private String sCanonicalurl;
    private WebView mWebview ;
    private final String wikiInstantSearchUrl = "https://en.wikipedia.org/wiki/Special:Search/";
    private final static String S_CANONICALURL= "canonicalurl";
    private String sTitle;
    private String sCoordinate;
    private final static int WIKI_SEARCH_RADIUS = 1000;


    private WikiGeoSearch getWikiGeoSearch()
    {
        String sQuery = "query";
        String sGeosearch = "geosearch";

        WikiGeoSearch wikiGeoSearch = new WikiGeoSearch();
        if(!Utils.isEmptyString(sCoordinate))
        {
            //Toast.makeText(this, sCoordinate, Toast.LENGTH_SHORT).show();
            wikiGeoSearch.setGscoord(sCoordinate);
        }
        wikiGeoSearch.setAction(sQuery);
        wikiGeoSearch.setFormat(WTAPIConstants.JSON);
        wikiGeoSearch.setList(sGeosearch);
        wikiGeoSearch.setGsradius(WIKI_SEARCH_RADIUS);
        return wikiGeoSearch;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadIntentExtraText();
        mWebview  = new WebView(this);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        final Activity activity = this;
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        if (WTAPIConstants.WIKI_GEO_SEARCH_ENABLED)
        {
            mloadManager.setListener(this);
            mloadManager.loadDataFromServer(WTAPIConstants.LOAD_WIKI_GEO_SEARCH, getWikiGeoSearch());
        }
        else
        {
            wikiInstantSearch();
        }
    }

    private void loadIntentExtraText() {
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            String extraText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (extraText.contains(WTAPIConstants.SPLIT_TOKEN))
            {
                String[] contents = extraText.split(WTAPIConstants.SPLIT_TOKEN);
                sTitle = contents[0];
                sCoordinate = contents[1];
            }
            else
            {
                sTitle = extraText;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wiki_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return true;
            } else {
                super.onBackPressed();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataSuccessLoad(int loaderId, String response) {
        if(loaderId == WTAPIConstants.LOAD_WIKI_GEO_SEARCH) {
            WikiGeoQueryResult wikiGeoQueryResult = ResponseParser.getWikiGeoQueryResult(response);
            WikiGeoSearchResults wikiGeoSearchResults = wikiGeoQueryResult.getWikiGeoQueryResult();
            if(wikiGeoSearchResults != null){
                WikiGeoSearchResult wikiGeoSearchResult = getBestMatch(wikiGeoSearchResults);
                if (wikiGeoSearchResult != null)
                {
                    loadWikiPage(wikiGeoSearchResult.getPageid());
                }
                else
                {
                    wikiInstantSearch();
                }
            }
        } else if (loaderId == WTAPIConstants.LOAD_WIKI_PAGE_SEARCH) {
            if (response.contains(S_CANONICALURL) && !Utils.responseContainsError(response))
            {
                String[] ret = response.split(S_CANONICALURL);
                String containsUrl = ret[1];
                sCanonicalurl = containsUrl.substring(3, containsUrl.length() - 5);
                mWebview.loadUrl(sCanonicalurl);
                setContentView(mWebview);
            } 
            else
            {
                wikiInstantSearch();
            }
        }
    }

    private WikiGeoSearchResult getBestMatch(WikiGeoSearchResults results)
    {
        WikiGeoSearchResult bestMatchRet = null;
        double bestAccuracy = WTAPIConstants.WIKI_TITLE_SIMILARITY_THRESHOLD;
        for (WikiGeoSearchResult item: results.getWikiGeoSearchResult_list())
        {
            double currentAccuracy = Utils.similarity(item.getWikiGeoResultTitle(), sTitle);
            if (currentAccuracy > bestAccuracy)
            {
                bestMatchRet = item;
                bestAccuracy = currentAccuracy;
            }
        }
        return bestMatchRet;
    }

    private void wikiInstantSearch() {
        mWebview.loadUrl(wikiInstantSearchUrl + sTitle);
        setContentView(mWebview);
    }

    public void loadWikiPage(int nPageId)
    {
        String sQuery = "query";
        String sInfo = "info";
        String sUrl = "url";
        //http://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&pageids=1544800&inprop=url
        WikiPageSearch wikiPageSearch = new WikiPageSearch();
        wikiPageSearch.setAction(sQuery);
        wikiPageSearch.setFormat(WTAPIConstants.JSON);
        wikiPageSearch.setProp(sInfo);
        wikiPageSearch.setInprop(sUrl);
        wikiPageSearch.setPageIds(nPageId);
        mloadManager.loadDataFromServer(WTAPIConstants.LOAD_WIKI_PAGE_SEARCH, wikiPageSearch);
    }

    @Override
    public void onDataLoadFailed(int loaderId, VolleyError error) {
        if (loaderId == WTAPIConstants.LOAD_WIKI_GEO_SEARCH)
        {
            WTLog.error(LOG_TAG, "cannot complete wiki geo search");

        } else if (loaderId == WTAPIConstants.LOAD_WIKI_PAGE_SEARCH)
        {
            WTLog.error(LOG_TAG, "cannot complete wiki page search");
        }

        wikiInstantSearch();
    }
}
