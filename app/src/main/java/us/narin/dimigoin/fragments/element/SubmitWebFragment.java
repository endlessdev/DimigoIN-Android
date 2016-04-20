package us.narin.dimigoin.fragments.element;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import us.narin.dimigoin.R;
import us.narin.dimigoin.util.Session;

/**
 * Created by coderi on 2016. 4. 20..
 */
public class SubmitWebFragment extends Fragment {

    // URL
    private String url;

    // Root View
    private View rootView;

    // Submit WebView
    private WebView webView;
    private WebSettings webSettings;

    public SubmitWebFragment setUrl(String url) {
        this.url = url;

        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_submit, container, false);

        webView = (WebView) rootView.findViewById(R.id.submit_webview);

        webView.setWebViewClient(new WebViewClient());

        // WEBVIEW Setting
        webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.loadUrl(url);

        return rootView;
    }
}
