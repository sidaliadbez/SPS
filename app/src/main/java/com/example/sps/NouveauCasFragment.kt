package com.example.sps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nouveau_cas.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NouveauCasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NouveauCasFragment : Fragment(), AdapterView.OnItemSelectedListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var v: View
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.FRENCH)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.FRANCE)
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      /*  val adapter = ArrayAdapter.createFromResource(context!!,R.array.array_ressource,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerwilaya.adapter=adapter
        spinnerwilaya.onItemSelectedListener=this*/

        v = inflater.inflate(R.layout.fragment_nouveau_cas, container, false)

      //  radioGroup =v.findViewById(R.id.grouperadio) // Inflate the layout for this fragment



        /*radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ radioGroup: RadioGroup, i: Int ->
            fun onCheckedChanged(group: RadioGroup,checkedId:Int )   {
                // checkedId is the RadioButton selected
                when(checkedId){
                    R.id.radio_one->{
                        println("11111111111111")
                    }
                    R.id.radio_two->{
                        println("2222222222222222")
                    }

                }

            }
        })*/

        return v

        if (grouperadio!=null){
            grouperadio.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId==R.id.radio_one){
                    Toast.makeText(context,"111111111111111111111111",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"2222222222222222",Toast.LENGTH_SHORT).show()
                    println("2222222222222222")
                }
            }
        }

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NouveauCasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NouveauCasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text = parent?.getItemAtPosition(position).toString()
        if (parent != null) {
            Toast.makeText(parent.context,text, Toast.LENGTH_SHORT).show()
        }
    }
    fun checkButton(v: View?) {
        val radioId: Int? = radioGroup.checkedRadioButtonId
        //var radioButton = radioId?.let { findViewById<RadioButton>(it) }
        var radioButton = radioId?.let { v?.findViewById<View>(it) } as RadioButton?
        Toast.makeText(
            context, "Selected Radio Button: " + radioButton?.text,
            Toast.LENGTH_SHORT
        ).show()
    }

}
