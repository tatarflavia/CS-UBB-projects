#pragma checksum "E:\School\practica\WebAppFacultyManagement\WebAppFacultyManagement\Pages\Contact.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "18f334d996c37b5acc548f06594f7b8ac2039312"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(WebAppFacultyManagement.Pages.Pages_Contact), @"mvc.1.0.razor-page", @"/Pages/Contact.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.RazorPages.Infrastructure.RazorPageAttribute(@"/Pages/Contact.cshtml", typeof(WebAppFacultyManagement.Pages.Pages_Contact), null)]
namespace WebAppFacultyManagement.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#line 1 "E:\School\practica\WebAppFacultyManagement\WebAppFacultyManagement\Pages\_ViewImports.cshtml"
using WebAppFacultyManagement;

#line default
#line hidden
#line 2 "E:\School\practica\WebAppFacultyManagement\WebAppFacultyManagement\Pages\_ViewImports.cshtml"
using WebAppFacultyManagement.Models;

#line default
#line hidden
#line 3 "E:\School\practica\WebAppFacultyManagement\WebAppFacultyManagement\Pages\_ViewImports.cshtml"
using Microsoft.AspNetCore.Http;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"18f334d996c37b5acc548f06594f7b8ac2039312", @"/Pages/Contact.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"bc35663e9220f934d99fab727febfa66c55102c7", @"/Pages/_ViewImports.cshtml")]
    public class Pages_Contact : global::Microsoft.AspNetCore.Mvc.RazorPages.Page
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#line 3 "E:\School\practica\WebAppFacultyManagement\WebAppFacultyManagement\Pages\Contact.cshtml"
  
    ViewData["Title"] = "Contact";

#line default
#line hidden
            BeginContext(71, 1406, true);
            WriteLiteral(@"

<h1 style=""color:darkcyan""><b>Location</b></h1>
<h3 style=""color:darkred"">Lower School (ages 3-11)</h3>
<address>
    Woodside Campus & The Lodge<br />
    49 Woodside Avenue,<br />
    London, N12 8SY<br />
    <abbr title=""Phone"">Administration Office (ages 3-6):</abbr>
    020 8920 0651<br />
    <abbr title=""Phone"">Administration Office (ages 6-11):</abbr>
    020 8920 0642<br />
    <abbr title=""Fax"">Fax:</abbr>
    +44(0)20 8445 0835
</address>

<h1 style=""color:darkcyan""><b>Contact Us</b></h1>
<h5>For <b>admissions</b> enquiries, please contact +44 (0)20 8920 0637 or email <a href=""mailto:admissions@gilbertschool.org"">admissions@gilbertschool.org</a></h5>
<h5>For <b>finance</b> enquiries, please email <a href=""mailto:finance@gilbertschool.org"">finance@gilbertschool.org</a></h5>
<h5>For all <b>other</b> enquiries, please call 020 8920 0600 or email <a href=""mailto:office@gilbertschool.org"">office@gilbertschool.org</a></h5>


<h3 style=""color:cornflowerblue"">Specific Contacts:</h3");
            WriteLiteral(@">
<h4 style=""color:darkred"">Lower School (ages 3-11)</h4>
<address>
    Matt Parkin<br />
    Lower School Principal<br />
    Woodside Campus & The Lodge<br />
    49 Woodside Avenue,<br />
    London, N12 8SY<br />
    <abbr title=""Phone"">Administration Office:</abbr>
    020 8920 0651<br />
    <abbr title=""Fax"">Fax:</abbr>
    +44(0)20 8445 0835
</address>



");
            EndContext();
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<ContactModel> Html { get; private set; }
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.ViewDataDictionary<ContactModel> ViewData => (global::Microsoft.AspNetCore.Mvc.ViewFeatures.ViewDataDictionary<ContactModel>)PageContext?.ViewData;
        public ContactModel Model => ViewData.Model;
    }
}
#pragma warning restore 1591
