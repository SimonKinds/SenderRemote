package io.kindstrom.senderremote.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;
import io.kindstrom.senderremote.presentation.view.fragment.InputNamingFragment;
import io.kindstrom.senderremote.presentation.view.fragment.OutputNamingFragment;
import io.kindstrom.senderremote.presentation.view.fragment.PortNamingFragment;

public class PortNamingActivity extends BaseActivity implements PortNamingFragment.SenderCreationNavigator {
    private static final String INTENT_EXTRA_AMOUNT_OF_INPUTS = "amount_of_inputs";
    private static final String INTENT_EXTRA_AMOUNT_OF_OUTPUTS = "amount_of_outputs";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static Intent getCallingIntent(Context context, int amountOfInputs, int amountOfOutputs) {
        Intent intent = new Intent(context, PortNamingActivity.class);
        intent.putExtra(INTENT_EXTRA_AMOUNT_OF_INPUTS, amountOfInputs);
        intent.putExtra(INTENT_EXTRA_AMOUNT_OF_OUTPUTS, amountOfOutputs);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        ButterKnife.bind(this);

        setupToolbar();
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.port_naming, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accept:
                navigateToSenderCreationView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupViewPager() {
        Bundle extras = getIntent().getExtras();

        int amountOfInputs = extras.getInt(INTENT_EXTRA_AMOUNT_OF_INPUTS);
        int amountOfOutputs = extras.getInt(INTENT_EXTRA_AMOUNT_OF_OUTPUTS);

        viewPager.setAdapter(new PortNamingAdapter(getSupportFragmentManager(),
                amountOfInputs,
                amountOfOutputs));

        tabLayout.addTab(tabLayout.newTab().setText(R.string.input));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.output));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void navigateToSenderCreationView() {
        PortNamingAdapter adapter = (PortNamingAdapter) viewPager.getAdapter();
        String[] inputNames = adapter.getFragment(PortNamingAdapter.INDEX_INPUT).getPortNames();
        String[] outputNames = adapter.getFragment(PortNamingAdapter.INDEX_OUTPUT).getPortNames();

        Intent intent = new Intent();
        intent.putExtra(SenderCreateActivity.RESULT_EXTRA_INPUT_NAMES, inputNames);
        intent.putExtra(SenderCreateActivity.RESULT_EXTRA_OUTPUT_NAMES, outputNames);
        setResult(RESULT_OK, intent);
        finish();
    }

    private class PortNamingAdapter extends FragmentPagerAdapter {
        static final int INDEX_INPUT = 0;
        static final int INDEX_OUTPUT = 1;
        private final SparseArray<PortNamingFragment> fragments = new SparseArray<>(2);
        private final int amountOfInputs;
        private final int amountOfOutputs;

        PortNamingAdapter(FragmentManager fm, int amountOfInputs, int amountOfOutputs) {
            super(fm);
            this.amountOfInputs = amountOfInputs;
            this.amountOfOutputs = amountOfOutputs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case INDEX_INPUT:
                    return InputNamingFragment.newInstance(amountOfInputs);
                case INDEX_OUTPUT:
                    return OutputNamingFragment.newInstance(amountOfOutputs);
            }
            return null;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PortNamingFragment frag = (PortNamingFragment) super.instantiateItem(container, position);
            fragments.append(position, frag);

            return frag;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            fragments.delete(position);

            super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return 2;
        }

        PortNamingFragment getFragment(int pos) {
            return fragments.get(pos);
        }
    }
}
