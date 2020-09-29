using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Assignment1
{
    public partial class Form1 : Form
    {
       
        private String connectionString = "Server=ARMIN\\SQLEXPRESS;Database=Movie Rental Database;Trusted_Connection=True";
        private SqlConnection connection = null;

        public Form1()
        {
       
            try
            {
                connection = new SqlConnection(connectionString);
                connection.Open();
            }
            catch (SqlException e) {
                MessageBox.Show(e.Message, "Error connecting to the database",MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            InitializeComponent();
        }



        
        private void buttonDisplayAllKidsProgramms_Click(object sender, EventArgs e)
        {
            DataSet dataSetManagers = new DataSet();
            SqlDataAdapter dataAdapterManagers = new SqlDataAdapter("select * from KidsProgramme", connection);
            dataAdapterManagers.Fill(dataSetManagers, "KidsProgramme");
            dataGridViewPrograms.DataSource = dataSetManagers.Tables["KidsProgramme"];
        }
        
        
        

        private void buttonDisplayAllSchoolsForSelectedProgramm_Click(object sender, EventArgs e)
        {
            dataGridViewSchools.Refresh();
            int selectedRow = dataGridViewPrograms.CurrentCell.RowIndex;
            DataGridViewRow row = dataGridViewPrograms.Rows[selectedRow];
            string selectedId = Convert.ToString(row.Cells["KidsProgrammeId"].Value);
            DataSet dataSetSchools = new DataSet();
            SqlDataAdapter dataAdapterSchools = new SqlDataAdapter("select * from Schools where KidsProgrammeId="+selectedId, connection);
            dataAdapterSchools.Fill(dataSetSchools, "Schools");
            dataGridViewSchools.DataSource = dataSetSchools.Tables["Schools"];
        }

        

        private void buttonAddSchool_Click(object sender, EventArgs e)
        {
            int selectedRow = dataGridViewPrograms.CurrentCell.RowIndex;
            DataGridViewRow row = dataGridViewPrograms.Rows[selectedRow];
            string selectedId = Convert.ToString(row.Cells["KidsProgrammeId"].Value);

            DataSet schools = new DataSet();
            SqlDataAdapter addingDataAdapter = new SqlDataAdapter();

            SqlCommand cmd = new SqlCommand("insert into Schools(Name,NrOfKids,KidsProgrammeId) values (@Name,@Nr,@FK)", connection);
            cmd.Parameters.Add("@Name", SqlDbType.VarChar);
            cmd.Parameters["@Name"].Value = textBoxNameOfSchool.Text;
            cmd.Parameters.Add("@Nr", SqlDbType.Int);
            string number= textBoxNrOfKids.Text;
            int nbConv = Convert.ToInt32(number);
            cmd.Parameters["@Nr"].Value = nbConv;
            int newId = Convert.ToInt32(selectedId);
            cmd.Parameters.Add("@FK", SqlDbType.Int);
            cmd.Parameters["@FK"].Value = newId;

            addingDataAdapter.InsertCommand = cmd;
            addingDataAdapter.InsertCommand.ExecuteNonQuery();

        }

        private void buttonUpdateSchool_Click(object sender, EventArgs e)
        {
            //take id from school instance
            int selectedRow = dataGridViewSchools.CurrentCell.RowIndex;
            DataGridViewRow row = dataGridViewSchools.Rows[selectedRow];
            string selectedId = Convert.ToString(row.Cells["SchoolId"].Value);

            DataSet schools = new DataSet();
            SqlDataAdapter updatingDataAdapter = new SqlDataAdapter();

            SqlCommand cmd = new SqlCommand("update Schools set Name=@Name, NrOfKids=@Nr where SchoolId="+selectedId, connection);
            cmd.Parameters.Add("@Name", SqlDbType.VarChar);
            cmd.Parameters["@Name"].Value = textBoxNameOfSchool.Text;
            cmd.Parameters.Add("@Nr", SqlDbType.Int);
            string number = textBoxNrOfKids.Text;
            int nbConv = Convert.ToInt32(number);
            cmd.Parameters["@Nr"].Value = nbConv;

            updatingDataAdapter.UpdateCommand = cmd;
            updatingDataAdapter.UpdateCommand.ExecuteNonQuery();
        }

        private void buttonRemoveSchool_Click(object sender, EventArgs e)
        {
            //take id from school instance
            int selectedRow = dataGridViewSchools.CurrentCell.RowIndex;
            DataGridViewRow row = dataGridViewSchools.Rows[selectedRow];
            string selectedId = Convert.ToString(row.Cells["SchoolId"].Value);

            DataSet schools = new DataSet();
            SqlDataAdapter deletingDataAdapter = new SqlDataAdapter();

            SqlCommand cmd = new SqlCommand("delete from Schools where SchoolId=" + selectedId, connection);
         

            deletingDataAdapter.DeleteCommand = cmd;
            deletingDataAdapter.DeleteCommand.ExecuteNonQuery();
        }

       

       
    }
}
