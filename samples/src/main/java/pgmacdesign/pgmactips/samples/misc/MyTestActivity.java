package pgmacdesign.pgmactips.samples.misc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pgmacdesign.pgmactips.adaptersandlisteners.GenericRecyclerviewAdapter;
import com.pgmacdesign.pgmactips.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmactips.biometricutilities.BiometricVerification;
import com.pgmacdesign.pgmactips.biometricutilities.FingerprintException;
import com.pgmacdesign.pgmactips.customui.MultiColorLine;
import com.pgmacdesign.pgmactips.datamodels.SamplePojo;
import com.pgmacdesign.pgmactips.misc.CustomAnnotationsBase;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConfig;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConstants;
import com.pgmacdesign.pgmactips.misc.TempString;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.RetrofitClient;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.RetrofitParser;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.serviceapiinterfaces.ProfanityCheckerAPICalls;
import com.pgmacdesign.pgmactips.networkclasses.retrofitutilities.serviceapiinterfaces.ProfanityCheckerInterface;
import com.pgmacdesign.pgmactips.networkclasses.sslsocketsandprotocols.SSLProtocolOptions;
import com.pgmacdesign.pgmactips.networkclasses.volleyutilities.VolleyUtilities;
import com.pgmacdesign.pgmactips.stackmanagement.StackManager;
import com.pgmacdesign.pgmactips.stackmanagement.StackManagerException;
import com.pgmacdesign.pgmactips.utilities.CameraMediaUtilities;
import com.pgmacdesign.pgmactips.utilities.ContactUtilities;
import com.pgmacdesign.pgmactips.utilities.DatabaseUtilities;
import com.pgmacdesign.pgmactips.utilities.DateUtilities;
import com.pgmacdesign.pgmactips.utilities.GsonUtilities;
import com.pgmacdesign.pgmactips.utilities.ImageUtilities;
import com.pgmacdesign.pgmactips.utilities.L;
import com.pgmacdesign.pgmactips.utilities.MalwareUtilities;
import com.pgmacdesign.pgmactips.utilities.MiscUtilities;
import com.pgmacdesign.pgmactips.utilities.NumberUtilities;
import com.pgmacdesign.pgmactips.utilities.PermissionUtilities;
import com.pgmacdesign.pgmactips.utilities.SharedPrefs;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;
import com.pgmacdesign.pgmactips.utilities.SystemUtilities;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import pgmacdesign.pgmactips.samples.activitysamples.SampleDBClass;
import pgmacdesign.pgmactips.samples.activitysamples.SampleDBClassKotlin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Test activity for experimenting with various Library components
 * Created by pmacdowell on 8/12/2016.
 */
@CustomAnnotationsBase.RequiresDependency(requiresDependencies = {CustomAnnotationsBase.Dependencies.Retrofit2,
        CustomAnnotationsBase.Dependencies.Retrofit2GSONConverter, CustomAnnotationsBase.Dependencies.GSON,
        CustomAnnotationsBase.Dependencies.OkHttp3LoggingInterceptor, CustomAnnotationsBase.Dependencies.Okio})
public class MyTestActivity  extends Activity implements View.OnClickListener {

    //Please note! these are for testing purposes only. I do not own the rights to the images below, I am referencing the URL link for testing loading times of images
    private static final String LOTR_TEST_URL_1 = "https://vignette.wikia.nocookie.net/lotr/images/8/87/Ringstrilogyposter.jpg/revision/latest?cb=20070806215413";
    private static final String LOTR_TEST_URL_2 = "https://vignette.wikia.nocookie.net/lotr/images/3/3a/The_Lord_of_the_Rings_Characters.jpg/revision/latest?cb=20150328111911";
    private static final String LOTR_TEST_URL_3 = "https://i.imgur.com/SmXu3j7.jpg";
    private static final String LOTR_TEST_URL_4 = "https://vignette.wikia.nocookie.net/lotr/images/7/79/Slider_-_One_Ring.jpg/revision/latest/scale-to-width-down/670?cb=20150328112455";
    private static final String LOTR_TEST_URL_5 = "http://fullhdwallpapers.ru/image/movies/5422/karta-sredizemya.jpg";
    private static final String LOTR_TEST_URL_6 = "https://i.imgur.com/CbNxBf7.jpg"; //Super large (4000x2000)

    private ScrollView testing_layout_scrollview;
    private ImageView image1, image2, image3, image4, image5, image6;
    private Button button;
    private RecyclerView testing_layout_recyclerview;

    private DatabaseUtilities dbUtilities;
    private CameraMediaUtilities cam;
    private BiometricVerification biometricVerification;
   // private MultipurposeEditText et;
    private static final String CUSTOM_STRING = "-PAT";
    private ContactUtilities contactUtilities;
    private OnTaskCompleteListener contactUtilsListener;

    private RelativeLayout testing_layout_rootview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.pgmacdesign.pgmactips.R.layout.testing_layout);
        //et = (MultipurposeEditText) this.findViewById(R.id.et);
        //et.setState(MultipurposeEditText.EditTextState.FOCUSED);
	    SampleMyApplication.getInstance();
        TextView tv1 = new TextView(this);
        tv1.setTextColor(getResources().getColor(com.pgmacdesign.pgmactips.R.color.black));
        this.testing_layout_rootview = this.findViewById(com.pgmacdesign.pgmactips.R.id.testing_layout_rootview);
        button = (Button) this.findViewById(com.pgmacdesign.pgmactips.R.id.button);
        button.setTag("button");
        button.setTransformationMethod(null);
        button.setOnClickListener(this);
        L.m("setOnClickListener");
	    testing_layout_recyclerview = (RecyclerView) this.findViewById(
	    		com.pgmacdesign.pgmactips.R.id.testing_layout_recyclerview);
	    testing_layout_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.testing_layout_scrollview = (ScrollView) this.findViewById(com.pgmacdesign.pgmactips.R.id.testing_layout_scrollview);
        this.testing_layout_scrollview.setVisibility(View.GONE);
        this.image1 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image1);
        this.image2 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image2);
        this.image3 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image3);
        this.image4 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image4);
        this.image5 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image5);
        this.image6 = (ImageView) this.findViewById(com.pgmacdesign.pgmactips.R.id.image6);
        L.Toast(this, "dd");

//        this.init2();
//        this.init3();
//        this.init5();
//        this.testDB2();
	    this.startDisplayMetricTests();
	    
	    L.m("TESTING TIME CALL: ");
	    String str1 = DateUtilities.convertMillisecondsToTimeString(System.currentTimeMillis(),
			    true, true);
	    String str2 = DateUtilities.convertMillisecondsToTimeString(PGMacTipsConstants.ONE_WEEK * 3 + (PGMacTipsConstants.ONE_SECOND),
			    true, false);
	    String str3 = DateUtilities.convertMillisecondsToTimeString(PGMacTipsConstants.ONE_MINUTE * 4 + (PGMacTipsConstants.ONE_WEEK),
			    false, true);
	    String str4 = DateUtilities.convertMillisecondsToTimeString(PGMacTipsConstants.ONE_SECOND * 2 + (PGMacTipsConstants.ONE_YEAR),
			    false, false);
	    L.m(str1);
	    L.m(str2);
	    L.m(str3);
	    L.m(str4);
    }

    private void startDisplayMetricTests(){
    
    }
    
    private SharedPrefs sp;
    @SuppressLint("NewApi")
    private void init5(){
        try {
            sp = SharedPrefs.getSharedPrefsInstance(this, "pattest1", new TempString("pattest4"));
//            init5Clear();
//            init5Save();
        } catch (GeneralSecurityException ge){
            ge.printStackTrace();
            sp = SharedPrefs.getSharedPrefsInstance(this, "pattest1");
        }
	
//	    sp.clearAllPrefs(true);
//	    L.m("Initial load, printing out all prefs");
//	    MiscUtilities.printOutHashMap(sp.getAllPrefs());

	
        init5Save();
        try {
	        sp.disableEncryption();
        } catch (Exception e){e.printStackTrace();}
	    init5Save();
        L.m("\nFinished saving non-encrypted values\n");
	    init5Get();
	    try {
		    sp.reEnableEncryption();
	    } catch (Exception e){e.printStackTrace();}
	    try {
		    sp.changePassword(new TempString("pattest991"));
	    } catch (GeneralSecurityException ge){
		    ge.printStackTrace();
	    }
	    init5Save();
	    L.m("Finished re-saving encrypted values");
//        init6Save();
        init5Get();
	    try {
		    sp.disableEncryption();
	    } catch (Exception e){e.printStackTrace();}
        sp.clearAllPrefs(true, true);
        if(true){
            return;
        }
	
	
//	    L.m("After saving via Init5Save(), printing out all prefs");
//	    MiscUtilities.printOutHashMap(sp.getAllPrefs());
	    
        L.m("\n");
	    init5Get();
	
//	    L.m("After Getting via Init5Get(), printing out all prefs");
//	    MiscUtilities.printOutHashMap(sp.getAllPrefs());
	    
//        L.m("Printing out data before changePassword called");
//        MiscUtilities.printOutHashMap(sp.getAllPrefs());
        L.m("\n");


        if(true){
            return;
        }

        try {
            sp.disableEncryption();
            sp.save("non_encrypted_stuff", "sure, why not");
            L.m("\n");
            L.m("Printing out data after disabling encryption and adding 1");
            MiscUtilities.printOutHashMap(sp.getAllPrefs());
            L.m("\n");

            sp.reEnableEncryption();

            L.m("\n");
            L.m("Printing out data after calling reEnableEncryption()");
            MiscUtilities.printOutHashMap(sp.getAllPrefs());
            L.m("\n");

        } catch (Exception e){
            e.printStackTrace();
        }

        if(true){
            return;
        }
        try {
            sp.changePassword(new TempString("pattest991"));
        } catch (GeneralSecurityException ge){
            ge.printStackTrace();
        }
        L.m("\n");
        L.m("Printing out data after changePassword called");
        MiscUtilities.printOutHashMap(sp.getAllPrefs());
        L.m("\n");
//        init5Clear();
    }

    private static final String KEY_STR = "STRING_TEST_STRING_LONGER";
    private static final String KEY_BOOL = "BOOL_TEST";
    private static final String KEY_DBL = "DOUBLE_TEST";
    private static final String KEY_LONG = "LONG_TEST";
    private static final String KEY_INT = "INT_TEST";

    private void init5Clear(){
        sp.clearPref(KEY_STR);
        sp.clearPref(KEY_BOOL);
        sp.clearPref(KEY_DBL);
        sp.clearPref(KEY_LONG);
        sp.clearPref(KEY_INT);
    }

    private void init5Save(){
        L.m("Starting init5 Save");
        sp.save(KEY_STR, "Key_str should be a lengthy String, one, in fact, that may be too large");
        sp.save(KEY_BOOL, true);
        sp.save(KEY_DBL, 123.123123);
        sp.save(KEY_LONG, 555555555555555L);
        sp.save(KEY_INT, 321);
        sp.save("email", "email@email.com");
        sp.save("pw", "password1");
	    L.m("Finished init5 Save");
    }
    
	private void init5Get(){
		L.m("Starting init5 Get");
		String str = sp.getString(KEY_STR, "nope");
		boolean bool = sp.getBoolean(KEY_BOOL, false);
		double dbl = sp.getDouble(KEY_DBL, -1.1);
		long lng = sp.getLong(KEY_LONG, -1);
		int intx = sp.getInt(KEY_INT, -1);
		String email = sp.getString("email", null);
		String pw = sp.getString("pw", null);
		L.m("str == " + str);
		L.m("bool == " + bool);
		L.m("dbl == " + dbl);
		L.m("lng == " + lng);
		L.m("intx == " + intx);
		L.m("Finished init5 Get");
	}
	
    private void init6Save(){
        L.m("save shared prefs encrypted");
        sp.save(KEY_STR, "DIFFERENT_worked?");
        sp.save(KEY_BOOL, true);
        sp.save(KEY_DBL, 321.321321321);
        sp.save(KEY_LONG, 11111111111L);
        sp.save(KEY_INT, 222);
    }


    @SuppressLint("MissingPermission")
    private void init3(){
        if(Build.VERSION.SDK_INT >= 23) {
            this.biometricVerification = new BiometricVerification(
                    new OnTaskCompleteListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onTaskComplete(Object result, int customTag) {
                            switch (customTag){
                                case BiometricVerification.TAG_AUTHENTICATION_FAIL:
                                    //Authentication failed / finger does not match
                                    boolean fail = (boolean) result;
                                    break;

                                case BiometricVerification.TAG_AUTHENTICATION_SUCCESS:
                                    //Authentication success / finger matches
                                    boolean success = (boolean) result;
                                    break;

                                case BiometricVerification.TAG_AUTHENTICATION_ERROR:
                                    //Error (IE called stopFingerprintAuth() or onStop() triggered)
                                    String knownAuthenticationError = (String) result;
                                    break;

                                case BiometricVerification.TAG_AUTHENTICATION_HELP:
                                    //Authentication did not work, help string passed
                                    String helpString = (String) result;
                                    break;

                                case BiometricVerification.TAG_GENERIC_ERROR:
                                    //Some error has occurred
                                    String genericError = (String) result;
                                    break;
                            }
                        }
                    }, this, "my_key_name");
            try {
                if(this.biometricVerification.isCriteriaMet()) {
                    this.biometricVerification.startFingerprintAuth();
                }
            } catch (FingerprintException e){
                e.printStackTrace();
            }
        }
    }

    private void init2(){
	    GenericRecyclerviewAdapter adapter = new GenericRecyclerviewAdapter(new GenericRecyclerviewAdapter.MultipurposeRecyclerviewLink() {
		    @Override
		    public void onBindViewTriggered(RecyclerView.ViewHolder holder0, int position) {
		    	L.m("onBindView triggered from the activity side, working?");
			    MyTestHolder holder = (MyTestHolder) holder0;
			    holder.button.setText("AWESOMESAUCE");
		    }
	    }, this, com.pgmacdesign.pgmactips.R.layout.testing_layout, MyTestHolder.class);
	    testing_layout_recyclerview.setHasFixedSize(true);
	    testing_layout_recyclerview.setAdapter(adapter);
	    SamplePojo sp1 = new SamplePojo();
	    List<SamplePojo> samplePojos = new ArrayList<>();
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    adapter.setListObjects(samplePojos);
    }

	public static class MyTestHolder extends RecyclerView.ViewHolder {
    	private Button button;
    	private MultiColorLine multi_color_line;
		public MyTestHolder(View itemView) {
			super(itemView);
			multi_color_line = (MultiColorLine) itemView.findViewById(com.pgmacdesign.pgmactips.R.id.multi_color_line);
			button = (Button) itemView.findViewById(com.pgmacdesign.pgmactips.R.id.button);
		}
	}

    private <E extends Enum<E>> void  init(){

        //contactQuery();
        //temp();
        //temp2();



        //Custom stuff here
        dbUtilities = new DatabaseUtilities(this);

        //writeDBStuff();
        //moveDBFile();
        //queryDB();
        //deleteStuff();
        //deleteCustom();
        //deleteAll();
        //superDeleteEverything();

	    List<Enum> testEnum1s = new ArrayList<>();
	    testEnum1s.add(TestEnum1.ONE);
	    testEnum1s.add(TestEnum1.TWO);
	    testEnum1s.add(TestEnum1.THREE);
	    testEnum1s.add(TestEnum1.FOUR);
	    testEnum1s.add(TestEnum1.FIVE);
	    List<Enum> testEnum2s = new ArrayList<>();
	    testEnum2s.add(TestEnum2.A);
	    testEnum2s.add(TestEnum2.B);
	    testEnum2s.add(TestEnum2.C);
	    testEnum2s.add(TestEnum2.D);
	    List<Enum> testEnum3s = new ArrayList<>();
	    testEnum3s.add(TestEnum3.Pat);
	    testEnum3s.add(TestEnum3.Mac);

	    Map<Integer, List<Enum>> myEnums = new HashMap<>();
	    myEnums.put(1, testEnum1s);
	    myEnums.put(2, testEnum2s);
	    myEnums.put(3, testEnum3s);

	    Map<Integer, Enum> myInitialEnums = new HashMap<>();
	    myInitialEnums.put(1, TestEnum1.ONE);
	    myInitialEnums.put(2, TestEnum2.A);
	    myInitialEnums.put(3, TestEnum3.Pat);
	    try {
		    StackManager s = new StackManager(myEnums, myInitialEnums);
		    s.appendToTheStack(1, TestEnum1.THREE);

	    } catch (StackManagerException e1){
	    	L.m("e1 == " + e1.toString());
	    }
    }

    public static enum TestEnum1 {
        ONE, TWO, THREE, FOUR, FIVE
    }
	public static enum TestEnum2 {
		A, B, C, D
	}
	public static enum TestEnum3 {
		Pat, Mac
	}


	private void moveDBFile(){
        //Check camera permissions
        PermissionUtilities perm = PermissionUtilities.getInstance(this);
        if(perm.startPermissionsRequest(new PermissionUtilities.permissionsEnum[]{
                PermissionUtilities.permissionsEnum.WRITE_EXTERNAL_STORAGE,
                PermissionUtilities.permissionsEnum.READ_EXTERNAL_STORAGE})) {
            dbUtilities.copyDBToDownloadDirectory(MyTestActivity.this, null);
        }
    }

    private void temp(){
        L.m(SystemUtilities.getPackageName());

    }
    private void temp2(){
        L.m("further package testing");
        //4 - test
        ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
        int counter = 0;
        for(PackageInfo packageInfo : res){
            L.m("position " + counter + " - " + packageInfo.packageName);
            // TODO: 8/18/2016 read through package info in docs sometime. tons of info
        }
        //PackageManager pm = getApplicationContext().getPackageManager();
        //List<PackageInfo> packs = pm.getInstalledPackages(0);
    }
    public String getPackageName(Context context) {
        return context.getPackageName();
    }

    @SuppressLint("MissingPermission")
    private void contactQuery(){
	    this.contactUtilsListener = new OnTaskCompleteListener() {
		    @Override
		    public void onTaskComplete(Object result, int customTag) {
			    switch (customTag){

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_EMAIL:
					    List<ContactUtilities.Contact> allContacts5 = (List<ContactUtilities.Contact>) result;
					    L.m("size of returned list (Email) == " +
							    (MiscUtilities.isListNullOrEmpty(allContacts5) ? 0
									    : allContacts5.size()));
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_PHONE:
					    List<ContactUtilities.Contact> allContacts4 = (List<ContactUtilities.Contact>) result;
					    L.m("size of returned list (phone) == " +
							    (MiscUtilities.isListNullOrEmpty(allContacts4) ? 0
									    : allContacts4.size()));
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_ADDRESS:
					    List<ContactUtilities.Contact> allContacts3 = (List<ContactUtilities.Contact>) result;
					    L.m("size of returned list (address) == " +
							    (MiscUtilities.isListNullOrEmpty(allContacts3) ? 0
									    : allContacts3.size()));
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_NAME:
					    List<ContactUtilities.Contact> allContacts2 = (List<ContactUtilities.Contact>) result;
					    L.m("size of returned list (name) == " +
							    (MiscUtilities.isListNullOrEmpty(allContacts2) ? 0
									    : allContacts2.size()));
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_PROGRESS_UPDATE:
					    //L.m("TAG_CONTACT_QUERY_PROGRESS_UPDATE");
					    Float flt = (Float) result;
					    if(flt != null){
					    	L.m("Progress Update: " + NumberUtilities.getFloat(flt) + "%");
					    }
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_NO_RESULTS:
					    L.m("TAG_CONTACT_QUERY_NO_RESULTS");
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_MISSING_CONTACT_PERMISSION:
					    L.m("TAG_CONTACT_QUERY_MISSING_CONTACT_PERMISSION");
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_UNKNOWN_ERROR:
					    L.m("TAG_CONTACT_QUERY_UNKNOWN_ERROR");
					    break;

				    case PGMacTipsConstants.TAG_CONTACT_QUERY_ALL_MERGED_RESULTS:
					    List<ContactUtilities.Contact> allContacts = (List<ContactUtilities.Contact>) result;
					    L.m("size of returned list == " +
							    (MiscUtilities.isListNullOrEmpty(allContacts) ? 0
									    : allContacts.size()));
					    break;

			    }
		    }
	    };
	    this.contactUtilities = new ContactUtilities
			    .Builder(this, contactUtilsListener)
			    .onlyIncludeContactsWithPhotos()
			    .shouldUpdateSearchProgress()
			    .build();

//	    this.contactUtilities.queryContacts(
//	    		new ContactUtilities.SearchTypes[]{ContactUtilities.SearchTypes.ADDRESS,
//					    ContactUtilities.SearchTypes.EMAIL, ContactUtilities.SearchTypes.NAME,
//					    ContactUtilities.SearchTypes.PHONE}, null);

	    this.contactUtilities.getAllContacts();
	    //this.contactUtilities.getAllContacts("Pat");
    }


    private void deleteAll(){
        dbUtilities.deleteAllPersistedObjects(true, false);
    }


    private void superDeleteEverything(){
        dbUtilities.deleteEntireDB(true, false);
    }

    private void testPhoto(){
        cam = new CameraMediaUtilities(this, this, new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                L.m("custom tag = " + customTag);
            }
        });
        cam.startPhotoProcess(CameraMediaUtilities.SourceType.GALLERY);
    }

    private void testLoadingAnimation(){
        //Removed on 2017-07-05 Due to problems with compiling
        //Dialog progressDialog = PGMacCustomProgressBar.buildCaliforniaSVGDialog(this, true);
        //progressDialog.show();
    }

    private void doWebCall(){


        ProfanityCheckerAPICalls.checkProfanityAsynchronous(this,
                new OnTaskCompleteListener() {
                    @Override
                    public void onTaskComplete(Object result, int customTag) {
                        L.m("web call done");
                        if(customTag == PGMacTipsConstants.TAG_RETROFIT_CALL_SUCCESS_STRING){
                            L.m("result == " + result.toString());
                        } else if(customTag == PGMacTipsConstants.TAG_RETROFIT_CALL_SUCCESS_BOOLEAN){
                            L.m("result == " + ((Boolean)result).toString());
                        }
                    }
                }, "word");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(CameraMediaUtilities.doesCodeBelongToUtility(requestCode)){
            cam.afterOnActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkMalware(){
        List<String> mylist = MalwareUtilities.checkForMalware(this);
        L.m("Number of infections: " + mylist.size());
        L.Toast(this, "Number of infections: " + mylist.size());
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {
	
	    loadTestCall();
    	
    	if(true){
    		return;
	    }
	    
	    this.startActivity(new Intent(MyTestActivity.this, SampleDBClassKotlin.class));
	    
    	if(true){
    		return;
	    }
	    
	    this.init5();
	    
	    if(true){
	    	return;
	    }
	    
        testPicassoImageLoads();

        if(true){
            return;
        }

        testNewWebClient();

        if(true){
            return;
        }

        String toSend = "https://www.ssllabs.com/ssltest/viewMyClient.html";
        testWeb2(toSend);


//        init5Get();
        //doWebCall();
        /*
        TimerUtilities.startTimer(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                L.m("result received from timerutilities");
                testLoadingAnimation();
            }
        });
        */
        //doWebCall();
        //showGIFLoader();
        //loadTestCall();

	    if(PermissionUtilities.permissionsRequestShortcutReturn(this,
			    new PermissionUtilities.permissionsEnum[]{
	    		PermissionUtilities.permissionsEnum.READ_CONTACTS})) {
	    	contactQuery();
	    }

    }

    private void testPicassoImageLoads(){
        try {
            this.testing_layout_scrollview.setVisibility(View.VISIBLE);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_1, this.image1, com.pgmacdesign.pgmactips.R.color.black);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_2, this.image2, com.pgmacdesign.pgmactips.R.color.blue);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_3, this.image3, com.pgmacdesign.pgmactips.R.color.black);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_4, this.image4, com.pgmacdesign.pgmactips.R.color.blue);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_5, this.image5, com.pgmacdesign.pgmactips.R.color.black);
            ImageUtilities.setImageWithPicasso(LOTR_TEST_URL_6, this.image6, com.pgmacdesign.pgmactips.R.color.blue);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private TestSSLInterface service;
    private void testNewWebClient(){
        L.m("testNewWebClient()");
        if(service == null) {
            RetrofitClient.Builder b = new RetrofitClient.Builder(TestSSLInterface.class, TestSSLInterface.BASE_URL);
            b.forceAcceptAllCertificates(true, false);
            b.setSSLProtocolOption(SSLProtocolOptions.TLSv1dot2);
            service = b.build().buildServiceClient();
        }
        if(service != null){
            Call<ResponseBody> call = service.checkTLS();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    L.m("Response was successful? " + response.isSuccessful());
                    if(response.isSuccessful()){
                        try {
                            L.m("RESPONSE == \n" + response.body().string());
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            L.m("RESPONSE == \n" + response.errorBody().string());
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void testDB2(){
        if(dbUtilities == null) {
            dbUtilities = new DatabaseUtilities(this);
        }
        SamplePojo samplePojo = new SamplePojo();
        samplePojo.setAge(2);
        samplePojo.setGender("panstuffffff");
        samplePojo.setId(123123);
        samplePojo.setName("name");
        samplePojo.setStrs(Arrays.asList("test1", "test2", "test3", "okiedokie"));
        samplePojo.setFauxEnums(Arrays.asList(SamplePojo.MyFauxTestEnum.One,
                SamplePojo.MyFauxTestEnum.Two, SamplePojo.MyFauxTestEnum.Three));
        boolean bool = dbUtilities.persistObject(SamplePojo.class, samplePojo);
        L.m("save success? - " + bool);

        SamplePojo ss = (SamplePojo) dbUtilities.getPersistedObject(SamplePojo.class);
        if(ss == null){
            L.m("could not retrieve object");
        } else {
            L.m("successfully retrieved object: " + GsonUtilities.convertObjectToJson(ss, SamplePojo.class));
        }

        boolean dePersisted = dbUtilities.dePersistObject(SamplePojo.class);
        L.m("Successfully depersisted the object? == " + dePersisted);
    }

    private void testWeb2(@Nullable String url){
        if(StringUtilities.isNullOrEmpty(url)){
            return;
        }
        VolleyUtilities.makeGetRequest(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object o, int i) {
                if (i == VolleyUtilities.VOLLEY_REQUEST_SUCCESS_STRING) {
                    String response = (String) o;
                    L.m("RESPONSE == " + response);
                } else if (i == VolleyUtilities.VOLLEY_REQUEST_VOLLEY_ERROR) {
                    VolleyError ve = (VolleyError) o;
                    if(ve != null){
                        ve.printStackTrace();
                    } else {
                        L.m("VE null");
                    }
                } else if (i == VolleyUtilities.VOLLEY_REQUEST_NULL_RETURN) {
                    L.m("Null return");
                }
            }
        }, MyTestActivity.this, url, null, SSLProtocolOptions.TLSv1dot2, false);
    }

    private void makeMultiColorLine() {
        MultiColorLine line = (MultiColorLine) this.findViewById(com.pgmacdesign.pgmactips.R.id.multi_color_line);
        line.setAnimateStrokes(true, 1000);
        line.setDrawAsSingleLine(true);
        line.setDrawBoarderWithLine(false);
        line.setDrawDiagonally(false);
        line.setFps(MultiColorLine.FPS.FPS_90);
        line.setWidthOfLineStroke(40);
        line.setWidthOfBoarderStroke(8);
        line.setColorOfBoarderStroke(getResources().getColor(com.pgmacdesign.pgmactips.R.color.aqua));
        List<MultiColorLine.CustomStrokeObject> strokes = new ArrayList<>();

        MultiColorLine.CustomStrokeObject l1 = new MultiColorLine.CustomStrokeObject(
                50, 0, getResources().getColor(com.pgmacdesign.pgmactips.R.color.red)
        );
        MultiColorLine.CustomStrokeObject l2 = new MultiColorLine.CustomStrokeObject(
                50, 50, getResources().getColor(com.pgmacdesign.pgmactips.R.color.blue)
        );
        strokes.add(l1);
        strokes.add(l2);
        line.setLineStrokes(strokes);
    }

    private void showGIFLoader(){
        //ProgressBarUtilities.showGIFProgressDialog(this, R.drawable.got_fighttex_house_stark); //<-- Reference the gif you want here
        //ProgressBarUtilities.showGIFProgressDialog(this, R.drawable.but_why_gif);
    }

    private void loadTestCall(){
        String BASE_URL = "http://www.purgomalum.com/";
        ProfanityCheckerInterface serviceInterface = new RetrofitClient.Builder(
                ProfanityCheckerInterface.class, BASE_URL)
                .setTimeouts(60000,60000)
//		        .setRetryCount(0)
		        .callIsJSONFormat()
		        .shouldSaveResponseCookies(new OnTaskCompleteListener() {
			        @Override
			        public void onTaskComplete(Object result, int customTag) {
						try {
							HashSet<String> cookies = (HashSet<String>) result;
							if(!MiscUtilities.isSetNullOrEmpty(cookies)){
								L.m("Received callback from cookies header == ");
								MiscUtilities.printOutHashSet(cookies);
							}
						} catch (Exception e ){
							e.printStackTrace();
						}
			        }
		        })//, new String[]{"Content-Type"}) //Add this back in to print out the header listener for the String "Content-Type"
                .setLogLevel(HttpLoggingInterceptor.Level.BODY)
                .build().buildServiceClient();
        SamplePojo pojo = new SamplePojo();
//        Call call = serviceInterface.checkProfanity2(pojo);
        Call call = serviceInterface.checkProfanity("test word");
        RetrofitParser.parse(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                L.m("CALLBACK TAG == " + customTag);
                L.m("CALLBACK RESULT == " + result);
            }
        }, call, RetrofitParser.TYPE_BOOLEAN, RetrofitParser.TYPE_INTEGER,
		        1, 0, true);
    }

    interface TestSSLInterface {
        //Not used here, but left for example. Could technically use this if empty String is passed to Client class
        static final String BASE_URL = "https://www.ssllabs.com/";

        //API pathways
        static final String BASE1 = "/ssltest";
        static final String BASE2 = "/viewMyClient.html";

        @GET(BASE1 + BASE2)
        Call<ResponseBody> checkTLS();
    }
    
    @SuppressLint("ValidFragment")
    static class MyTestFrag extends Fragment {
	    @Override
	    public void onCreate(@Nullable Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
	    }
    }
	
	
	
}