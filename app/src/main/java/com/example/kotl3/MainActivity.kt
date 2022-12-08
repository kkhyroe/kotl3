package com.example.kotl3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotl3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var personItemList : MutableList<PersonItem> = mutableListOf()
    lateinit var adapter : PersonItemAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        populateList()
        setUpAdapter()

        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onContextItemSelected(item)
    }

    fun populateList() {
        var picId = "pic1"
        var name = "Исаак Ньютон"
        var dates = "1642 - 1727"
        var desc =
            "Английский физик, математик, механик и астроном, один из создателей классической физики и математического анализа"
        var gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic2"
        name = "Иоганн Гутенберг"
        dates = "1397 - 1468"
        desc =
            "Немецкий первопечатник, первый типограф Европы. В 1440-х годах создал способ книгопечатания подвижными литерами"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic3"
        name = "Адам Смит"
        dates = "1723 - 1790"
        desc =
            "Шотландский экономист и философ-этик, один из основоположников экономической теории как науки. Считается основателем классической политэкономии"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic4"
        name = "Микеланджело"
        dates = "1475 - 1564"
        desc =
            "Итальянский скульптор, художник, архитектор, поэт и мыслитель. Один из крупнейших мастеров эпохи Возрождения и раннего барокко"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic5"
        name = "Алан Тьюринг"
        dates = "1912 - 1954"
        desc =
            "Английский математик, логик, криптограф, оказавший существенное влияние на развитие информатики"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic6"
        name = "Джулиан Ассанж"
        dates = "1971 - ..."
        desc =
            "Австралийский интернет-журналист. В больших объёмах обнародовал сверхсекретные материалы о шпионских скандалах, коррупции в высших эшелонах власти и военных преступлениях"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic7"
        name = "Фридрих Ницше"
        dates = "1844 - 1900"
        desc =
            "Немецкий философ, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic8"
        name = "Стив Джобс"
        dates = "1955 - 2011"
        desc =
            "Американский предприниматель, изобретатель и промышленный дизайнер, получивший широкое признание в качестве пионера эры информационных технологий"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic9"
        name = "Данте Алигьери"
        dates = "1265 - 1321"
        desc =
            "Итальянский поэт, мыслитель, богослов, один из основоположников литературного итальянского языка, политический деятель"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))

        picId = "pic10"
        name = "Мюррей Ротбард"
        dates = "1926 - 1995"
        desc =
            "Американский политический философ, экономист, представитель австрийской школы экономической теории, чьи труды сыграли основную роль в развитии современного либертарианства"
        gender = "МУЖЧИНА"
        personItemList.add(PersonItem(picId = picId, name = name, dates = dates, desc = desc, gender = gender))
    }

    fun setUpAdapter() {
        adapter = PersonItemAdapter(this, personItemList)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)
    }
}