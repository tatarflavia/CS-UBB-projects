using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ExamASP.Models
{
    public class Asset
    {
        public Asset(int id, int userid, string name, string description, int value)
        {
            Id = id;
            Userid = userid;
            Name = name;
            Description = description;
            Value = value;
        }


        public Asset()
        {
        }
        public int Id { get; set; }

        
        public int Userid { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(30)]
        public string Name { get; set; }

        
        public string Description { get; set; }

        [Required]
        [Range(0, int.MaxValue, ErrorMessage = "Please enter valid integer Number")]
        public int Value { get; set; }

        
    }
}