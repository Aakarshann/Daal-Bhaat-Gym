<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="/pages/header.jsp"%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Page</title>
<link rel="index" href="adminheader.html">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/adminpage.css" />
</head>

<body>
	<!-- ========================= Main ==================== -->
	<div class="main">

		<!-- ======================= Cards ================== -->
		<div class="cardBox">
			<div class="card">
				<div>
					<div class="numbers">XX</div>
					<div class="cardName">Total Members</div>
				</div>

				<div class="iconBx">
					<ion-icon name="eye-outline"></ion-icon>
				</div>
			</div>

			<div class="card">
				<div>
					<div class="numbers">80</div>
					<div class="cardName">Plans Bought</div>
				</div>

				<div class="iconBx">
					<ion-icon name="cart-outline"></ion-icon>
				</div>
			</div>

			<div class="card">
				<div>
					<div class="numbers">284</div>
					<div class="cardName">Comments</div>
				</div>

				<div class="iconBx">
					<ion-icon name="chatbubbles-outline"></ion-icon>
				</div>
			</div>

			<div class="card">
				<div>
					<div class="numbers">$7,842</div>
					<div class="cardName">Total Earnings</div>
				</div>

				<div class="iconBx">
					<ion-icon name="cash-outline"></ion-icon>
				</div>
			</div>
		</div>

		<!-- ================ Order Details List ================= -->
		<div class="details">
			<div class="recentOrders">


				<table>
					<thead>
						<tr>
							<td>PlanID</td>
							<td>Plan Name</td>
							<td>Plan Duration</td>
							<td>Plan Price</td>
							<td>Plan Description</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="plan" items="${planLists}">
							<tr>
								<td>${plan.planID}</td>
								<td>${plan.planDurationDays}DayPlan</td>
								<td>${plan.planDurationDays}</td>
								<td>${plan.planPrice}</td>
								<td>${plan.planDescription}</td>
								<td>
									<button type="button"
										onclick="openUpdateForm('${plan.planID}', '${plan.planDurationDays}', '${plan.planPrice}', '${plan.planDescription}')">Update</button>
									<form id="deleteForm-${plan.planID}" method="post"
										action="<%=contextPath + StringUtils.SERVLET_URL_MANAGE_PLAN%>">
										<input type="hidden" name="<%=StringUtils.DELETE_ID %>"
											value="${plan.planID}" />
										<button type="button"
											onclick="confirmDelete('${plan.planID}')">Delete</button>
									</form>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- ================= New Customers ================ -->
			<div class="recentCustomers">
				<div class="cardHeader">
					<h2>!Gym Rats!</h2>
				</div>

				<table>
					<tbody>
						<c:forEach var="user" items="${userLists}">
							<tr>
								<td width="60px">
									<div class="imgBx">
									 <img src="../resources/images/user/${user.imageUrlFromPart}" class="card-img-top" 
            alt="" onerror="this.onerror=null; this.src='../resources/images/others/userimg.jpeg';">
										<
									</div>
								</td>
								<td>
									<h4>${user.firstName}
										${user.lastName} <br> <span>${user.username}</span>
									</h4>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="updateForm" style="display: none;">
		<h2>Update Plan</h2>
		<form id="updatePlanForm" method="post"
			action="<%=contextPath + StringUtils.SERVLET_URL_MANAGE_PLAN%>">
			<!-- Hidden input field for planID -->
			<input type="hidden" id="updatePlanID"
				name="<%=StringUtils.UPDATE_ID%>" value="">
			<!-- Add the name attribute in the input field above -->

			<!-- Display planID -->
			<label for="updatePlanID">PlanID:</label> <input type="text"
				id="updatePlanIDDisplay" name="planID" readonly>

			<!-- Other input fields -->
			<label for="updatePlanDurationDays">Plan Duration Dayst:</label> <input
				type="text" id="updatePlanDurationDays" name="planDurationDays"
				required> <label for="updatePlanPrice">Plan Price:</label> <input
				type="text" id="updatePlanPrice" name="planPrice" required>
			<label for="updatePlanDescription">Plan Description:</label> <input
				type="text" id="updatePlanDescription" name="planDescription"
				required>
			<button type="submit">Update</button>
		</form>
	</div>
	<!-- Form for adding a new plan -->
	<div class="container">
		<h1>Registration Form</h1>
		<form action="<%=contextPath%>/PlanServlet" method="post">
			<div class="row">
				<div class="col">
					<label for="planDurationDays">Plan Duration Days:</label> <input
						type="text" id="planDurationDays" name="planDurationDays"
						value="1" required>
				</div>
				<div class="col">
					<label for="planPrice">planPrice:</label> <input type="text"
						id="planPrice" name="planPrice" value="2" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="planDescription">planDescription:</label> <input
						type="text" id="planDescription" name="planDescription" value=""
						required>
				</div>
			</div>
			<button type="submit">Submit</button>
		</form>
		</div>
	<script>
		// JavaScript functions for updating and deleting plans
		function confirmDelete(planID) {
			if (confirm("Are you sure you want to delete this plan?")) {
				// Submit the delete form
				document.getElementById("deleteForm-" + planID).submit();
			}
		}

		function openUpdateForm(planID, planDurationDays, planPrice,
				planDescription) {
			document.getElementById("updatePlanID").value = planID; // Set value for hidden input
			document.getElementById("updatePlanIDDisplay").value = planID; // Display planID
			document.getElementById("updatePlanDurationDays").value = planDurationDays;
			document.getElementById("updatePlanPrice").value = planPrice;
			document.getElementById("updatePlanDescription").value = planDescription;
			document.getElementById("updateForm").style.display = "block";
		}
	</script>
	<!-- =========== Scripts =========  -->
	<script src="assets/js/main.js"></script>

	<!-- ====== ionicons ======= -->
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>