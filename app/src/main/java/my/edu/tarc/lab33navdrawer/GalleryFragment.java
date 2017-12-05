package my.edu.tarc.lab33navdrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment
{

    private static final int REQUEST_PHOTO = 1;
    private ImageView imageViewPhoto;
    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //LINK UI TO PROGRAM
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        imageViewPhoto = (ImageView) v.findViewById(R.id.imageViewPhoto);
        Button buttonGetPhoto = (Button) v.findViewById(R.id.buttonGetPhoto);
        buttonGetPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //go to gallery and select a photo
                Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT);
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                //Filter to only show results that can be "opened"
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                //Filter to show only images, using the image MIME data type
                intent.setType("image/*");

                startActivityForResult(intent, REQUEST_PHOTO);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK)
        {
            Uri uri = null;

            if (data != null)
            {
                uri  = data.getData();
                imageViewPhoto.setImageURI(uri);
            }
        }
    }
}
