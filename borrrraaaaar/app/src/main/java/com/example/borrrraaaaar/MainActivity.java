package com.example.borrrraaaaar;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements  View.OnDragListener, View.OnLongClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView puente,iglesia,mercado,rivera,marijaia,hucha;

    private LinearLayout LPuente, Lnada,LMercado,Lrivera,Lmarijaia,Lhucha;

    private View vaux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        implementEvents();
    }

    //Find all views and set Tag to all draggable views
    private void findViews() {
        puente = findViewById(R.id.lblpuente);
        iglesia = findViewById(R.id.lbliglesia);
        mercado = findViewById(R.id.lblmercado);
        rivera = findViewById(R.id.lblrivera);
        marijaia = findViewById(R.id.lblmarijaia);
        hucha = findViewById(R.id.lblhucha);

        LPuente=findViewById(R.id.right_layout);
        Lnada=findViewById(R.id.left_layout);
        LMercado=findViewById(R.id.LYmercado);
        Lrivera=findViewById(R.id.Lrivera);
        Lmarijaia=findViewById(R.id.Lmarijaia);
        Lhucha=findViewById(R.id.Lhucha);
    }


    //Implement long click and drag listener
    private void implementEvents() {
        //add or remove any view that you don't want to be dragged
        puente.setOnLongClickListener(this);
        iglesia.setOnLongClickListener(this);
        mercado.setOnLongClickListener(this);
        rivera.setOnLongClickListener(this);
        marijaia.setOnLongClickListener(this);
        hucha.setOnLongClickListener(this);

        //add or remove any layout view that you don't want to accept dragged vie
        findViewById(R.id.left_layout).setOnDragListener(this);
        findViewById(R.id.right_layout).setOnDragListener(this);
        findViewById(R.id.LYmercado).setOnDragListener(this);
        findViewById(R.id.Lrivera).setOnDragListener(this);
        findViewById(R.id.Lmarijaia).setOnDragListener(this);
        findViewById(R.id.Lhucha).setOnDragListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        // Create a new ClipData.
        // This is done in two steps to provide clarity. The convenience method
        // ClipData.newPlainText() can create a plain text ClipData in one step.

        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data//data to be dragged
                , shadowBuilder //drag shadow
                , view//local data about the drag and drop operation
                , 0//no needed flags
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
     //   view.setVisibility(View.INVISIBLE);
        vaux = view;
        return true;
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.

                    //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view

                    // Invalidate the view to force a redraw in the new tint
                    //  view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a YELLOW or any color tint to the View, when the dragged view entered into drag acceptable view
                // Return true; the return value is ignored.

             //   view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
//                view.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();






                // Invalidates the view to force a redraw
               // view.invalidate();

//                View v = (View) event.getLocalState();
//                ViewGroup owner = (ViewGroup) v.getParent();
//                owner.removeView(v);//remove the dragged view
                LinearLayout container = (LinearLayout) view;//caste the view into LinearLayout as our drag acceptable layout is LinearLayout
//                //container.addView(v);//Add the dragged view
//              //  v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE


                View v = (View) event.getLocalState();
                System.out.println(dragData+"|||||||||"+Lnada.getTag().toString());
                if(dragData.equals("puente")){
                    if(container.getTag().toString().equals(dragData)){
                        puente.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        puente.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }
                if(dragData.equals("iglesia")){
                    if(container.getTag().toString().equals(dragData)){
                        iglesia.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        iglesia.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }
                if(dragData.equals("mercado")){
                    if(container.getTag().toString().equals(dragData)){
                        mercado.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        mercado.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }
                if(dragData.equals("rivera")){
                    if(container.getTag().toString().equals(dragData)){
                        rivera.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        rivera.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }
                if(dragData.equals("marijaia")){
                    if(container.getTag().toString().equals(dragData)){
                        marijaia.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        marijaia.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }
                if(dragData.equals("hucha")){
                    if(container.getTag().toString().equals(dragData)){
                        hucha.setTextColor(getColor(android.R.color.holo_green_light));
                    }else{
                        hucha.setTextColor(getColor(android.R.color.holo_red_light));
                    }
                }

                // Returns true. DragEvent.getResult() will return true.
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
               // view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // Does a getResult(), and displays what happened.
                if (event.getResult()) {


                }
                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();


                   // vaux.setVisibility(View.VISIBLE);


                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }


}