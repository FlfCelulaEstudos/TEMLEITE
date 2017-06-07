package br.edu.flf.temleite;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private MyAppAdapter myAppAdapter;
    private ArrayList<Post> postArrayList;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.listView);
        postArrayList=new ArrayList<>();

        new GetProducts().execute();

    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView txtMarca,txtProduto;
            TextView txtAtl, txtLeite, txtOvo, txtGlutem, txtSoja;


        }

        public List<Post> parkingList;

        public Context context;
        ArrayList<Post> arraylist;

        private MyAppAdapter(List<Post> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<Post>();
            arraylist.addAll(parkingList);

        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_post, null);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.txtMarca = (TextView) rowView.findViewById(R.id.marca);
                viewHolder.txtProduto = (TextView) rowView.findViewById(R.id.produto);
                viewHolder.txtAtl = (TextView) rowView.findViewById(R.id.atualizacao);
                viewHolder.txtLeite = (TextView) rowView.findViewById(R.id.leite);
                viewHolder.txtOvo = (TextView) rowView.findViewById(R.id.ovo);
                viewHolder.txtGlutem = (TextView) rowView.findViewById(R.id.gluten);
                viewHolder.txtSoja = (TextView) rowView.findViewById(R.id.soja);

                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtMarca.setText(parkingList.get(position).getPostMarca() + "");
            viewHolder.txtProduto.setText(parkingList.get(position).getPostProduto() + "");
            viewHolder.txtAtl.setText("Atualização: " + verifyDictionaryProduct(parkingList.get(position).getPostAtualizacao()) + "");
            viewHolder.txtLeite.setText("Leite: " + verifyDictionaryProduct(parkingList.get(position).getPostLeite()) + "");
            viewHolder.txtOvo.setText("Ovo: " + verifyDictionaryProduct(parkingList.get(position).getPostOvo()) + "");
            viewHolder.txtGlutem.setText("Gluten: " + verifyDictionaryProduct(parkingList.get(position).getPostGluten()) + "");
            viewHolder.txtSoja.setText("Soja: " + verifyDictionaryProduct(parkingList.get(position).getPostSoja()) + "");

            return rowView;


        }

        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());

            parkingList.clear();
            if (charText.length() == 0) {
                parkingList.addAll(arraylist);

            } else {
                for (Post postDetail : arraylist) {
                    if (charText.length() != 0 && postDetail.getPostMarca().toLowerCase(Locale.getDefault()).contains(charText)) {
                        parkingList.add(postDetail);
                    }

                    else if (charText.length() != 0 && postDetail.getPostProduto().toLowerCase(Locale.getDefault()).contains(charText)) {
                        parkingList.add(postDetail);
                    }
                }
            }
            notifyDataSetChanged();
        }

        String verifyDictionaryProduct (String value){

            String traduction;

            if (value.equals(new String("0"))){
                traduction = "Não contém o alérgeno";
                return traduction;
            }else if (value.equals(new String("1"))){
                traduction = "Contém o alérgeno";
                return traduction;
            }else if (value.equals(new String("2"))){
                traduction = "Maquinário higienizado";
                return traduction;
            }else if (value.equals(new String("3"))){
                traduction =  "Derivado de soja";
                return traduction;
            }else {
                traduction = " --- ";
                return traduction;
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class GetProducts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Carregando...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://firebasestorage.googleapis.com/v0/b/aplv-c8444.appspot.com/o/lista_produtos.json?alt=media&token=62dcea8b-894f-47da-a35c-fec67db60e89";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(jsonStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Getting JSON Array node
                    JSONArray products = jsonObj.getJSONArray("products");

                    // looping through All Contacts
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        String marca = c.getString("Marca/Empresa");
                        String produto = c.getString("Produto");
                        String atualizacao = c.getString("Ultima Atualização");
                        String ovo = c.getString("Ovo");
                        String soja = c.getString("Soja");
                        String gluten = c.getString("Gluten");
                        String leite = c.getString("Leite");

                        // adding product to list
                        postArrayList.add(new Post(marca, produto, atualizacao, leite, ovo, gluten, soja));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            myAppAdapter=new MyAppAdapter(postArrayList,MainActivity.this);
            listView.setAdapter(myAppAdapter);

        }
    }
}
