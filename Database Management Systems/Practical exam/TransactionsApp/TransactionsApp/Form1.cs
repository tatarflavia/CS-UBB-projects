using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TransactionsApp
{
    public partial class Form1 : Form
    {

        private SqlConnection connection = null;
        private string connectionString = "Server=ARMIN\\SQLEXPRESS;Database=TransactionAtATMs;Trusted_Connection=True";

        private SqlDataAdapter daCards, daTransactions;
        private DataSet ds;
        private SqlCommandBuilder commandBuilderTransactions;
        private BindingSource bsCards, bsTransactions;

        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        //event handler for the button Update DB's click event
        private void btnUpdateDB_Click(object sender, EventArgs e)
        {
            //handle insert update delete on Transactions
            //we call the update method on our TransactionsDataAdaptor: propagates changes to the db
            //we can add remove update Transactions using the dataGridView, then we click the button and changes are sent to the db
            daTransactions.Update(ds, "Transactions");
        }

        private void connect()
        {
            try
            {
                connection = new SqlConnection(connectionString);
                connection.Open();
            }
            catch (SqlException e)
            {
                MessageBox.Show(e.Message, "Error connecting to the database", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        //event handler for the form's load event
        private void Form1_Load(object sender, EventArgs e)
        {
        
            //bind dataGridViews and bring data from the db 

            //instantiate the binding sources
            bsCards = new BindingSource();
            bsTransactions = new BindingSource();

            //bind the dataGridViews to the binding sources
            dgvCards.DataSource = bsCards;
            dgvTransactions.DataSource = bsTransactions;

            //get data from db
            connect();

            //instantiate dataSet and dataAdaptors
            ds = new DataSet();
            daCards = new SqlDataAdapter("SELECT * FROM Cards", connection);
            daTransactions = new SqlDataAdapter("SELECT * FROM Transactions", connection);

            //command builder:
            commandBuilderTransactions = new SqlCommandBuilder(daTransactions);

            //get data from db:retrieve Cards and Transactions: call fill method on the 2 data adaptors
            daCards.Fill(ds, "Cards");
            daTransactions.Fill(ds, "Transactions");
            //we'll have 2 data tables in our dataSet names Cards and Transactions containing all rows from respective tables(that's what the dataAdaptors do)
            //cards-parent table and transactions-child table

            //now we'll add a DataRelation to represent this relationship between cards and transactions: like primary key foreign key relationship
            DataRelation dataRelation = new DataRelation("CardsTransactions", ds.Tables["Cards"].Columns["CardID"], ds.Tables["Transactions"].Columns["CardID"]);
            ds.Relations.Add(dataRelation); //and add it to dataSet

            //now specify binding details for the 2 binding sources
            bsCards.DataSource = ds;
            bsCards.DataMember = "Cards"; //bind to Cards DataTable

            bsTransactions.DataSource = bsCards; //bind bsPosts to bsCards
            bsTransactions.DataMember = "CardsTransactions"; //use dataRelation CardsTransactions to filter the transactions of the current card

        }
    }
}
